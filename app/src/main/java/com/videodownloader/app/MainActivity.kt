package com.videodownloader.app

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.videodownloader.app.ui.screens.MainScreen
import com.videodownloader.app.ui.theme.VideoDownloaderTheme
import com.yausername.youtubedl_android.YoutubeDL

class MainActivity : ComponentActivity() {
    
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            initializeYoutubeDL()
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Request storage permission
        checkPermissions()
        
        // Initialize YoutubeDL
        initializeYoutubeDL()
        
        setContent {
            VideoDownloaderTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var sharedUrl by remember { mutableStateOf<String?>(null) }
                    
                    // Handle shared URL from intent
                    if (intent?.action == Intent.ACTION_SEND || intent?.action == Intent.ACTION_VIEW) {
                        val url = extractUrlFromIntent(intent)
                        if (url != null) {
                            sharedUrl = url
                        }
                    }
                    
                    MainScreen(
                        initialUrl = sharedUrl,
                        onUrlProcessed = { sharedUrl = null }
                    )
                }
            }
        }
    }
    
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        // Handle new shared URLs when app is already running
    }
    
    private fun extractUrlFromIntent(intent: Intent): String? {
        return when (intent.action) {
            Intent.ACTION_SEND -> {
                if (intent.type == "text/plain") {
                    intent.getStringExtra(Intent.EXTRA_TEXT)
                } else null
            }
            Intent.ACTION_VIEW -> {
                intent.data?.toString()
            }
            else -> null
        }
    }
    
    private fun checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
    
    private fun initializeYoutubeDL() {
        try {
            YoutubeDL.getInstance().init(application)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
