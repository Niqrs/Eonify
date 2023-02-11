package com.niqr.auth.ui.screens.forgot.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.niqr.core_ui.theme.EonifyTheme

@Composable
fun ColumnScope.ForgotNavigateUpButton(
    onClick: () -> Unit
) {
    IconButton( // Navigate Up Button
        modifier = Modifier
            .padding(vertical = 8.dp)
            .align(Alignment.Start),
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = EonifyTheme.colorScheme.onBackground
        )
    }
}