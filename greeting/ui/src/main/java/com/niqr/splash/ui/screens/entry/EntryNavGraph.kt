package com.niqr.splash.ui.screens.entry

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val EntryScreenRoutePattern = "entry"

internal fun NavController.navigateToEntry() {
    this.navigate(EntryScreenRoutePattern) {
        popUpTo(0)
        launchSingleTop = true
    }
}

internal fun NavGraphBuilder.entryScreen(
    onNavigateNext: () -> Unit
) {
    composable(
        route = EntryScreenRoutePattern
    ) {
        val viewModel: EntryViewModel = hiltViewModel()
        EntryScreen(
            uiEvent = viewModel.uiEvent,
            onEvent = viewModel::onEvent,
            uiState = viewModel.uiState,
            onNavigateNext = onNavigateNext
        )
    }
}