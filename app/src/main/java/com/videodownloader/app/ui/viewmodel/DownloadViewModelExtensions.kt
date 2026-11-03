package com.videodownloader.app.ui.viewmodel

// Extension functions for DownloadViewModel

internal fun DownloadViewModel.updateDownloadLine(downloadId: String, line: String) {
    _downloads.value = _downloads.value.map { download ->
        if (download.id == downloadId) {
            download.copy(statusLine = line)
        } else {
            download
        }
    }
}

internal fun DownloadViewModel.updateDownloadFile(downloadId: String, filePath: String) {
    _downloads.value = _downloads.value.map { download ->
        if (download.id == downloadId) {
            download.copy(filePath = filePath)
        } else {
            download
        }
    }
}

internal fun DownloadViewModel.updateDownloadError(downloadId: String, error: String) {
    _downloads.value = _downloads.value.map { download ->
        if (download.id == downloadId) {
            download.copy(errorMessage = error)
        } else {
            download
        }
    }
}
