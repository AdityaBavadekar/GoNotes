package com.adityaamolbavadekar.gonotes

import android.app.Application
import android.content.Context
import androidx.viewbinding.BuildConfig
import org.acra.config.mailSender
import org.acra.data.StringFormat
import org.acra.ktx.initAcra

class GoNotes : Application() {

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