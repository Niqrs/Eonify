package com.niqr.splash.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.niqr.core_ui.theme.EonifyTheme
import com.niqr.splash.ui.R

@Composable
internal fun SplashScreen(
    splashViewModel: SplashViewModel = hiltViewModel(),
    onSplashEnd: () -> Unit
) {
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
        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(
                containerColor = EonifyTheme.colorScheme.primary,
                contentColor = EonifyTheme.colorScheme.onPrimary
            ),
            onClick = onSplashEnd
        ) {
            Text(text = "Next")
        }
    }
}