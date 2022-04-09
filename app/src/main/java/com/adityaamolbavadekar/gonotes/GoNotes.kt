package com.adityaamolbavadekar.gonotes

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.multidex.MultiDex
import androidx.preference.PreferenceManager
import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteDatabase
import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteRepository
import com.adityaamolbavadekar.gonotes.features.search.source.RecentSearchesDatabase
import com.adityaamolbavadekar.gonotes.features.search.source.RecentSearchesRepository
import com.adityaamolbavadekar.gonotes.logger.GoNotesLogFormat
import com.adityaamolbavadekar.gonotes.logger.Logger.infoLog
import com.adityaamolbavadekar.gonotes.logger.Logger.installLogger
import com.hypertrack.hyperlog.HyperLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.acra.config.mailSender
import org.acra.data.StringFormat
import org.acra.ktx.initAcra
import java.util.*
import kotlin.properties.Delegates

class GoNotes : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { NoteDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { NoteRepository(database.dao) }
    private val searchHistoryDatabase by lazy { RecentSearchesDatabase.getDatabase(this) }
    val searchHistoryRepository by lazy { RecentSearchesRepository(searchHistoryDatabase.dao) }
    var nightMode by Delegates.notNull<Int>()

    override fun onCreate() {
        super.onCreate()
        HyperLog.initialize(this,GoNotesLogFormat(this))
        val prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        var nightModeName: String = "MODE_NIGHT_FOLLOW_SYSTEM"
        nightMode =
                //If version is less then Android 10 then set MODE_NIGHT_AUTO_BATTERY
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                nightModeName = "MODE_NIGHT_AUTO_BATTERY"
                MODE_NIGHT_AUTO_BATTERY
            } else {
                when (prefs.getInt("theme", 3)) {
                    0 -> {
                        nightModeName = "MODE_NIGHT_YES"
                        MODE_NIGHT_YES
                    }
                    1 -> {
                        nightModeName = "MODE_NIGHT_NO"
                        MODE_NIGHT_NO
                    }
                    2 -> {
                        nightModeName = "MODE_NIGHT_FOLLOW_SYSTEM"
                        MODE_NIGHT_FOLLOW_SYSTEM
                    }
                    else -> MODE_NIGHT_FOLLOW_SYSTEM
                }
            }
        setDefaultNightMode(nightMode)
        infoLog("${Build.VERSION.SDK_INT}_NIGHT_MODE=${nightModeName}[${nightMode}]")

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
        installLogger()
        initAcra {
            withEnabled(BuildConfig.appGoNotesDevLoggingEnabled)
            buildConfigClass = BuildConfig::class.java
            withReportSendSuccessToast("App problem occurred")
            withParallel(true)
            withReportFormat(StringFormat.KEY_VALUE_LIST)
            mailSender {
                withEnabled(BuildConfig.appGoNotesDevLoggingEnabled)
                withReportFileName("GoNotesFile${System.currentTimeMillis()}.txt")
                withMailTo("adityarbavadekar@gmail.com")
                withSubject("Sorry, but Go Notes crashed. Here is info on crash.")
                withBody("Date : ${Date(System.currentTimeMillis())}\nHere is attachment.")
            }
        }
    }

}