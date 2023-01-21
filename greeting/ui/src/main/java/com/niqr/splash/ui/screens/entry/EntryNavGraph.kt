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
            onPageChange = { page ->
                uiState = uiState.copy(
                    selectedPage = page
                )
            },
            onNavigateNext = {
                if (uiState.selectedPage == uiState.pages.lastIndex) {
                    onNavigateNext()
                } else {
                    uiState = uiState.copy(
                        selectedPage = uiState.selectedPage + 1
                    )
                }
            },
            onNavigateBack = {
//                if (uiState.selectedPage != uiState.pages.lastIndex) {
//                    uiState = uiState.copy(
//                        selectedPage = uiState.selectedPage - 1
//                    )
//                }
            }
        )
    }
}