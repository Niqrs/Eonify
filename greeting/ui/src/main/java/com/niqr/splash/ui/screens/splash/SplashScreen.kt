package com.niqr.splash.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.niqr.core_ui.theme.EonifyTheme
import com.niqr.splash.ui.R
import kotlinx.coroutines.flow.Flow

@Composable
internal fun SplashScreen(
    uiEvent: Flow<SplashEvent>,
    onAction: (SplashAction) -> Unit,
    onSplashEnd: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        onAction(SplashAction.OnSplashLaunched)
        uiEvent.collect {
            when(it) {
                SplashEvent.OnSplashEnd -> onSplashEnd()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .aspectRatio(1f),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo"
            )
            Text(
                text = "Eonify",
                modifier = Modifier.padding(top = 8.dp),
                color = EonifyTheme.colorScheme.textPrimaryHeader,
                style = EonifyTheme.typography.headlineMedium,
            )
        }
    }
}