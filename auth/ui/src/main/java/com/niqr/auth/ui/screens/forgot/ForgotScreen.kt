package com.niqr.auth.ui.screens.forgot

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.niqr.core_ui.theme.EonifyTheme

@Composable
internal fun ForgotScreen(
    uiState: ForgotUiState,
    onNavigateBack: () -> Unit
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
            onClick = onNavigateBack
        ) {
            Text(text = "Navigate Back")
        }
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = "Forgot",
        )
    }
}