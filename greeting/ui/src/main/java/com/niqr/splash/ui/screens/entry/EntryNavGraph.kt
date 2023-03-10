package com.niqr.splash.ui.screens.entry

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val EntryScreenRoutePattern = "entry"

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
            onAction = viewModel::onAction,
            uiState = viewModel.uiState,
            onNavigateNext = onNavigateNext
        )
    }
}