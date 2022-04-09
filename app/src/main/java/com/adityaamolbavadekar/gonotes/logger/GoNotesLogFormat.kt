package com.adityaamolbavadekar.gonotes.logger

import android.content.Context
import com.hypertrack.hyperlog.LogFormat

class GoNotesLogFormat(context: Context) : LogFormat(context) {

    override fun getFormattedLogMessage(
        logLevelName: String?,
        tag: String?,
        message: String?,
        timeStamp: String?,
        senderName: String?,
        osVersion: String?,
        deviceUUID: String?
    ): String {
        return "$timeStamp | $osVersion | $logLevelName | [$tag] : $message"
    }
}