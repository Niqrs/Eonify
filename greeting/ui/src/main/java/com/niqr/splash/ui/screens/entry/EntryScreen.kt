package com.niqr.splash.ui.screens.entry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.niqr.core_ui.theme.EonifyTheme

@Composable
internal fun EntryScreen(
    uiState: EntryUiState,
    onNavigateNext: () -> Unit,
    onNavigateBack: () -> Unit
) {
    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = uiState.entry.title
            )
            Text(
                text = uiState.entry.description
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            if (!uiState.isFirstEntry()) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = EonifyTheme.colorScheme.primary,
                        contentColor = EonifyTheme.colorScheme.onPrimary
                    ),
                    onClick = onNavigateBack
                ) {
                    Text(text = "Back")
                }
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = EonifyTheme.colorScheme.primary,
                    contentColor = EonifyTheme.colorScheme.onPrimary
                ),
                onClick = onNavigateNext,
            ) {
                Text(text = "Next")
            }
        }
    }
}