package com.niqr.auth.ui.screens.forgot.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.niqr.core_ui.theme.EonifyTheme

@Composable
fun ForgotDescription() {
    Text( // Description
        text = "It was popularised in the 1960s with" +
                " the release of Letraset sheetscontaining Lorem Ipsum.",
        style = EonifyTheme.typography.bodyMedium,
        color = EonifyTheme.colorScheme.textBody
    )
}