package com.adityaamolbavadekar.gonotes.usecases

data class LatestVersionInfo(
    var versionName : String,
    var versionCode : Int,
    var releaseNotes : String,
    var updateReleasedOn : String,
    var updateApkUrl : String
)
