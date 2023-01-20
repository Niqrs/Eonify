package com.niqr.splash.ui.screens.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
internal fun SplashScreen(
    onSplashEnd: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = "Splash"
        )
        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            onClick = onSplashEnd
        ) {
            Text(text = "Next")
        }
    }
}