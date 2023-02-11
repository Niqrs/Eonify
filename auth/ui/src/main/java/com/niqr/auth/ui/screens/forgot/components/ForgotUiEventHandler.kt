package com.niqr.auth.ui.screens.forgot.components

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.niqr.auth.ui.screens.forgot.ForgotEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun ForgotUiEventHandler(
    uiEvent: Flow<ForgotEvent>,
    snackbarHost: SnackbarHostState,
    onNavigateUp: () -> Unit
) {
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        uiEvent.collect {
            when(it) {
                ForgotEvent.NavigateUp -> onNavigateUp()
                is ForgotEvent.ShowSnackbar -> {
                    scope.launch {
                        if (snackbarHost.currentSnackbarData == null)
                            snackbarHost.showSnackbar(it.message)
                    }
                }
            }
        }
    }
}