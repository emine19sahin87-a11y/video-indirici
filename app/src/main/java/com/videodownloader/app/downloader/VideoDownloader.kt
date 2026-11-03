package com.videodownloader.app.downloader

import android.content.Context
import android.os.Environment
import com.videodownloader.app.models.DownloadQuality
import com.videodownloader.app.models.DownloadType
import com.yausername.youtubedl_android.YoutubeDL
import com.yausername.youtubedl_android.YoutubeDLRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class VideoDownloader(private val context: Context) {
    
    suspend fun download(
        url: String,
        quality: DownloadQuality,
        type: DownloadType,
        onProgress: (Float, Long, String) -> Unit
    ): Result<File> = withContext(Dispatchers.IO) {
        try {
            // Initialize YoutubeDL if not already
            if (!YoutubeDL.getInstance().isInitialized()) {
                YoutubeDL.getInstance().init(context)
            }
            
            val request = YoutubeDLRequest(url)
            val downloadDir = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                "VideoDownloader"
            )
            
            if (!downloadDir.exists()) {
                downloadDir.mkdirs()
            }
            
            request.addOption("-o", "${downloadDir.absolutePath}/%(title)s.%(ext)s")
            
            when (type) {
                DownloadType.VIDEO -> {
                    // Video format with quality
                    val formatString = when (quality) {
                        DownloadQuality.LOW -> "worst[height<=360]"
                        DownloadQuality.MEDIUM -> "best[height<=480]"
                        DownloadQuality.HIGH -> "best[height<=720]"
                        DownloadQuality.FULL_HD -> "best[height<=1080]"
                    }
                    request.addOption("-f", formatString)
                }
                DownloadType.AUDIO -> {
                    // Extract audio only
                    request.addOption("-x")
                    request.addOption("--audio-format", "mp3")
                    request.addOption("--audio-quality", "0")
                }
            }
            
            // Progress callback
            val response = YoutubeDL.getInstance().execute(request) { progress, etaInSeconds, line ->
                onProgress(progress, etaInSeconds, line)
            }
            
            val outputFile = File(response.out)
            Result.success(outputFile)
            
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    fun getSupportedPlatforms(): List<String> {
        return listOf(
            "YouTube",
            "TikTok", 
            "Instagram",
            "Facebook",
            "Twitter",
            "Reddit",
            "Vimeo",
            "Dailymotion"
        )
    }
}
