package com.niqr.splash.ui.screens.entry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val EntryGraphRoutePattern = "entry"

internal fun NavController.navigateToEntry() {
    this.navigate(EntryGraphRoutePattern) {
        popUpTo(0)
        launchSingleTop = true
    }
}

internal fun NavGraphBuilder.entryScreen(
    onNavigateNext: () -> Unit
) {
    composable(
        route = EntryGraphRoutePattern
    ) {
        var uiState by remember {
            mutableStateOf(EntryUiState())
        }

        EntryScreen(
            uiState = uiState,
            onNavigateNext = {
                if (uiState.isLastEntry()) {
                    onNavigateNext()
                } else {
                    println(uiState.entry)
                    uiState = uiState.copy(
                        entry = entries[uiState.entry.id + 1]
                    )
                    println(uiState.entry)
                }
            },
            onNavigateBack = {
                if (!uiState.isFirstEntry()) {
                    uiState = uiState.copy(
                        entry = entries[uiState.entry.id - 1]
                    )
                }
            }
        )
    }
}