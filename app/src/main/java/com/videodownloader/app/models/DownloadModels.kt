package com.videodownloader.app.models

enum class DownloadQuality {
    LOW,      // 360p
    MEDIUM,   // 480p
    HIGH,     // 720p
    FULL_HD   // 1080p
}

enum class DownloadType {
    VIDEO,
    AUDIO  // MP3
}

enum class DownloadStatus {
    PENDING,
    DOWNLOADING,
    COMPLETED,
    FAILED,
    CANCELLED
}

data class DownloadItem(
    val id: String,
    val url: String,
    val title: String,
    val status: DownloadStatus,
    val progress: Int,
    val quality: DownloadQuality,
    val type: DownloadType,
    val fileSize: String,
    val errorMessage: String,
    val filePath: String = "",
    val statusLine: String = ""
)
