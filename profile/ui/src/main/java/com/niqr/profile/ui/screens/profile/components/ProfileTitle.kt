package com.niqr.profile.ui.screens.profile.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.niqr.core_ui.theme.EonifyTheme
import com.niqr.core_ui.theme.Primary30

@Composable
fun ProfileTitle(
    text: String
) {
    Text(
        modifier = Modifier
            .padding(start = 16.dp),
        text = text,
        style = EonifyTheme.typography.titleMedium,
        color = Primary30
    )
}