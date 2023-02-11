package com.niqr.auth.ui.screens.signup.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.niqr.core_ui.theme.EonifyTheme

@Composable
fun SignupDivider() {
    Row( // Or
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Divider(modifier = Modifier.weight(1f), color = EonifyTheme.colorScheme.divider)
        Text(
            text = "Or",
            color = EonifyTheme.colorScheme.textContrast,
            style = EonifyTheme.typography.bodyMedium
        )
        Divider(modifier = Modifier.weight(1f), color = EonifyTheme.colorScheme.divider)
    }
}