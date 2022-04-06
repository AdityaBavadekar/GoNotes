package com.adityaamolbavadekar.gonotes

import android.app.Application
import android.content.Context
import androidx.viewbinding.BuildConfig
import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteDatabase
import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.acra.config.mailSender
import org.acra.data.StringFormat
import org.acra.ktx.initAcra

class GoNotes : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { NoteDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { NoteRepository(database.dao) }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
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

}