package com.niqr.eonify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.niqr.core_ui.theme.EonifyTheme
import com.niqr.eonify.utils.isFirstLaunch
import com.niqr.eonify.utils.setEonifyStatusBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setEonifyStatusBar()
        setContent {
            EonifyTheme {
                EonifyApp(isFirstLaunch())
            }
        }
    }
}