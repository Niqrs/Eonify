package com.niqr.auth.ui.screens.signup.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.niqr.core_ui.theme.EonifyTheme

@Composable
fun SignupHeader() {
    Text( // Header
        text = "Sign Up",
        style = EonifyTheme.typography.headlineMedium,
        color = EonifyTheme.colorScheme.textPrimaryHeader
    )
}