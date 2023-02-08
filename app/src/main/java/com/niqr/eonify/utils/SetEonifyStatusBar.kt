package com.niqr.eonify.utils

import android.app.Activity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat

fun Activity.setEonifyStatusBar() {
    WindowCompat.setDecorFitsSystemWindows(window, false)
    window.statusBarColor = Color.Transparent.toArgb()
    WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = !isNightMode()
}