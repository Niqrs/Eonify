package com.niqr.splash.ui.screens.entry.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.niqr.core_ui.theme.EonifyTheme

@Composable
fun EntryPageDescription(
    description: String
) {
    Text(
        text = description,
        modifier = Modifier.padding(top = 32.dp),
        color = EonifyTheme.colorScheme.textBody,
        style = EonifyTheme.typography.bodyLarge
    )
}