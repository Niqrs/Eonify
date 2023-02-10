package com.niqr.profile.ui.screens.profile.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.niqr.core_ui.theme.setDefaultStatusBarColor

@Composable
fun ProfileSystemUiController() {
    val uiController = rememberSystemUiController()
    val darkTheme = isSystemInDarkTheme()

    DisposableEffect(uiController) {
        uiController.setStatusBarColor(Color.Transparent, false)
        onDispose {
            uiController.setDefaultStatusBarColor(darkTheme)
        }
    }
}
