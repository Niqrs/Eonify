package com.niqr.eonify

import android.os.Bundle
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
    }

    private fun isFirstLaunch(): Boolean {
        val mainPreferences = getPreferences(0)
        val isFirstLaunch = mainPreferences.getBoolean("FirstLaunch", true)
        if (isFirstLaunch) mainPreferences.edit().putBoolean("FirstLaunch", false).apply()
        return isFirstLaunch
    }
}