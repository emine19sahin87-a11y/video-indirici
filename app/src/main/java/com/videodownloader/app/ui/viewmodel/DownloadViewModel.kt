package com.videodownloader.app.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.videodownloader.app.downloader.VideoDownloader
import com.videodownloader.app.models.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class DownloadViewModel(application: Application) : AndroidViewModel(application) {
    private val videoDownloader = VideoDownloader(application)
    private val _url = MutableStateFlow("")
    val url: StateFlow<String> = _url.asStateFlow()
    
    private val _selectedQuality = MutableStateFlow(DownloadQuality.HIGH)
    val selectedQuality: StateFlow<DownloadQuality> = _selectedQuality.asStateFlow()
    
    private val _selectedType = MutableStateFlow(DownloadType.VIDEO)
    val selectedType: StateFlow<DownloadType> = _selectedType.asStateFlow()
    
    private val _isValidUrl = MutableStateFlow(false)
    val isValidUrl: StateFlow<Boolean> = _isValidUrl.asStateFlow()
    
    private val _downloads = MutableStateFlow<List<DownloadItem>>(emptyList())
    val downloads: StateFlow<List<DownloadItem>> = _downloads.asStateFlow()
    
    fun setUrl(newUrl: String) {
        _url.value = newUrl
        _isValidUrl.value = validateUrl(newUrl)
    }
    
    fun setQuality(quality: DownloadQuality) {
        _selectedQuality.value = quality
    }
    
    fun setDownloadType(type: DownloadType) {
        _selectedType.value = type
    }
    
    private fun validateUrl(url: String): Boolean {
        if (url.isBlank()) return false
        
        val patterns = listOf(
            // TikTok
            "tiktok\\.com",
            "vm\\.tiktok\\.com",
            "vt\\.tiktok\\.com",
            // YouTube
            "youtube\\.com/watch",
            "youtu\\.be",
            "youtube\\.com/shorts",
            // Instagram
            "instagram\\.com/p/",
            "instagram\\.com/reel/",
            "instagram\\.com/tv/"
        )
        
        return patterns.any { pattern ->
            Regex(pattern, RegexOption.IGNORE_CASE).containsMatchIn(url)
        }
    }
    
    fun startDownload() {
        viewModelScope.launch {
            val newDownload = DownloadItem(
                id = UUID.randomUUID().toString(),
                url = _url.value,
                title = extractTitleFromUrl(_url.value),
                status = DownloadStatus.PENDING,
                progress = 0,
                quality = _selectedQuality.value,
                type = _selectedType.value,
                fileSize = "",
                errorMessage = ""
            )
            
            _downloads.value = listOf(newDownload) + _downloads.value
            
            // Simulate download progress
            simulateDownload(newDownload.id)
        }
    }
    
    private fun extractTitleFromUrl(url: String): String {
        return when {
            url.contains("tiktok.com") -> "TikTok Video"
            url.contains("youtube.com") || url.contains("youtu.be") -> "YouTube Video"
            url.contains("instagram.com") -> "Instagram Video"
            else -> "Video"
        }
    }
    
    private suspend fun simulateDownload(downloadId: String) {
        val download = _downloads.value.find { it.id == downloadId } ?: return
        
        // Update to downloading
        updateDownloadStatus(downloadId, DownloadStatus.DOWNLOADING)
        
        try {
            val result = videoDownloader.download(
                url = download.url,
                quality = download.quality,
                type = download.type
            ) { progress, eta, line ->
                // Update progress
                updateDownloadProgress(downloadId, (progress * 100).toInt())
                updateDownloadLine(downloadId, line)
            }
            
            result.fold(
                onSuccess = { file ->
                    updateDownloadStatus(downloadId, DownloadStatus.COMPLETED)
                    updateDownloadFile(downloadId, file.absolutePath)
                },
                onFailure = { error ->
                    updateDownloadStatus(downloadId, DownloadStatus.FAILED)
                    updateDownloadError(downloadId, error.message ?: "İndirme başarısız")
                }
            )
        } catch (e: Exception) {
            updateDownloadStatus(downloadId, DownloadStatus.FAILED)
            updateDownloadError(downloadId, e.message ?: "Bilinmeyen hata")
        }
    }
    
    private fun updateDownloadStatus(downloadId: String, status: DownloadStatus) {
        _downloads.value = _downloads.value.map { download ->
            if (download.id == downloadId) {
                download.copy(status = status)
            } else {
                download
            }
        }
    }
    
    private fun updateDownloadProgress(downloadId: String, progress: Int) {
        _downloads.value = _downloads.value.map { download ->
            if (download.id == downloadId) {
                download.copy(
                    progress = progress,
                    fileSize = "${(progress * 0.5).toInt()} MB / 50 MB"
                )
            } else {
                download
            }
        }
    }
    
    fun cancelDownload(downloadId: String) {
        updateDownloadStatus(downloadId, DownloadStatus.CANCELLED)
    }
    
    fun retryDownload(downloadId: String) {
        viewModelScope.launch {
            updateDownloadStatus(downloadId, DownloadStatus.PENDING)
            updateDownloadProgress(downloadId, 0)
            simulateDownload(downloadId)
        }
    }
    
    fun deleteDownload(downloadId: String) {
        _downloads.value = _downloads.value.filter { it.id != downloadId }
    }
}
