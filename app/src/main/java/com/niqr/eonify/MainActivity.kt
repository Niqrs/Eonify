package com.niqr.eonify

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.niqr.core_ui.theme.EonifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EonifyTheme {
                EonifyApp(isFirstLaunch())
            }
        }
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun isFirstLaunch(): Boolean {
        val mainPreferences = getPreferences(0)
        val isFirstLaunch = mainPreferences.getBoolean("FirstLaunch", true)
        if (isFirstLaunch) mainPreferences.edit().putBoolean("FirstLaunch", false).apply()
        return isFirstLaunch
    }
}