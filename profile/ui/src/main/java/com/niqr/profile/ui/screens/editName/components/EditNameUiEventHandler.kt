package com.niqr.profile.ui.screens.editName.components

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.niqr.profile.ui.screens.editName.EditNameEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun EditNameUiEventHandler(
    uiEvent: Flow<EditNameEvent>,
    snackbarHost: SnackbarHostState,
    onNavigateUp: () -> Unit,
    onApply: () -> Unit,
) {
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        uiEvent.collect {
            when(it) {
                EditNameEvent.NavigateUp -> onNavigateUp()
                EditNameEvent.OnApply -> onApply()
                is EditNameEvent.ShowSnackbar -> {
                    if (snackbarHost.currentSnackbarData == null) {
                        scope.launch {
                            snackbarHost.showSnackbar(it.message)
                        }
                    }
                }
            }
        }
    }
}