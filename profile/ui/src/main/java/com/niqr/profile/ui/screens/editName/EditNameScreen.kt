package com.niqr.profile.ui.screens.editName

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@Composable
fun EditNameScreen(
    uiState: EditNameUiState,
    uiEvent: Flow<EditNameEvent>,
    onAction: (EditNameAction) -> Unit,
    onNavigateUp: () -> Unit,
    onApply: () -> Unit
) {
    LaunchedEffect(Unit) {
        uiEvent.collect {
            when(it) {
                EditNameEvent.NavigateUp -> onNavigateUp()
                EditNameEvent.OnApply -> onApply()
                is EditNameEvent.ShowSnackbar -> {}
            }
        }
    }
    
    Text(text = "EditNameScreen")
}