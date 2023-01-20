package com.niqr.auth.ui.screens.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SignupScreen(
    uiState: SignupUiState,
    onNavigateToSignin: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = "sign-un",
        )
        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            onClick = onNavigateToSignin
        ) {
            Text(text = "Navigate To Signin")
        }
    }
}