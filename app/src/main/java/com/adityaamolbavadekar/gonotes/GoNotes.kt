package com.adityaamolbavadekar.gonotes

import android.app.Application
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.core.os.BuildCompat
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import androidx.preference.PreferenceManager
import androidx.viewbinding.BuildConfig
import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteDatabase
import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.acra.config.mailSender
import org.acra.data.StringFormat
import org.acra.ktx.initAcra
import kotlin.properties.Delegates

class GoNotes : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { NoteDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { NoteRepository(database.dao) }
    var nightMode by Delegates.notNull<Int>()

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate Called")

        val prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        nightMode =
            //If version is less then Android 10 then set MODE_NIGHT_AUTO_BATTERY
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) { MODE_NIGHT_AUTO_BATTERY }
            else {
                when(prefs.getInt("appTheme",3)){
                    0 -> MODE_NIGHT_YES
                    1 -> MODE_NIGHT_NO
                    2 -> MODE_NIGHT_FOLLOW_SYSTEM
                    else -> MODE_NIGHT_FOLLOW_SYSTEM
                }
            }
        setDefaultNightMode(nightMode)
        Log.d(TAG,"NIGHT_MODE=${nightMode}")

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
        initAcra {
            buildConfigClass = BuildConfig::class.java
            withReportSendSuccessToast("Mail Sent!")
            withParallel(true)
            withEnabled(true)
            withReportFormat(StringFormat.KEY_VALUE_LIST)

            mailSender {
                withReportFileName("GoNotesFile${System.currentTimeMillis()}")
                withMailTo("adityarbavadekar@gmail.com")
                withSubject("Sorry, but Go Notes crashed. Here is info on crash.")
                withBody("Here is attachment.")
                withEnabled(true)
            }
        }
    }

    companion object {
        const val TAG = "GoNotesApplication"
    }

}