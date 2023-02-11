package com.niqr.profile.ui.screens.bio.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.niqr.core_ui.theme.EonifyTheme

@Composable
fun BioDescription() {
    Text(
        text = "You can add a few lines about yourself.",
        style = EonifyTheme.typography.bodyMedium,
        color = EonifyTheme.colorScheme.textHint
    )
}