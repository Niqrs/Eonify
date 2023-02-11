package com.niqr.auth.ui.screens.forgot.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.niqr.core_ui.theme.EonifyTheme

@Composable
fun ForgotHeader() {
    Text( // Header
        text = "Forgot Password",
        style = EonifyTheme.typography.headlineMedium,
        color = EonifyTheme.colorScheme.textPrimaryHeader,
        textAlign = TextAlign.Center
    )
}