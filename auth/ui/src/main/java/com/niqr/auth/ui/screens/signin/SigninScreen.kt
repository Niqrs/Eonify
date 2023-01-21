package com.niqr.auth.ui.screens.signin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.niqr.core_ui.theme.EonifyTheme

@Composable
internal fun SigninScreen(
    uiState: SigninUiState,
    onNavigateToForgot: () -> Unit,
    onNavigateToSignup: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            modifier = Modifier
                .align(Alignment.TopCenter),
            colors = ButtonDefaults.buttonColors(
                containerColor = EonifyTheme.colorScheme.primary,
                contentColor = EonifyTheme.colorScheme.onPrimary
            ),
            onClick = onNavigateToSignup
        ) {
            Text(text = "Navigate To Signup")
        }
        Text(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
            text = "sign-in",
        )
        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(
                containerColor = EonifyTheme.colorScheme.primary,
                contentColor = EonifyTheme.colorScheme.onPrimary
            ),
            onClick = onNavigateToForgot
        ) {
            Text(text = "Navigate To Forgot")
        }
    }
}