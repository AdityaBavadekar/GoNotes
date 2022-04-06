package com.adityaamolbavadekar.gonotes.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class CheckForUpdatesUseCase {

    suspend operator fun invoke(): LatestVersionInfo =
        withContext(Dispatchers.IO) {
            delay(300 * 1000)
            LatestVersionInfo("", 1, "", "", "")
        }

}