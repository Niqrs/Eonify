package com.niqr.auth.ui.screens.signin.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.niqr.core_ui.theme.EonifyTheme

@Composable
fun SigninHeader() {
    Text( // Header
        text = "Sign In",
        style = EonifyTheme.typography.headlineMedium,
        color = EonifyTheme.colorScheme.textPrimaryHeader
    )
}