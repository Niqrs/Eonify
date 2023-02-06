package com.niqr.profile.ui.screens.bio

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val BioScreenRoutePattern = "bioScreen"

internal fun NavController.navigateToBioScreen() {
    this.navigate(BioScreenRoutePattern) {
        launchSingleTop = true
    }
}

internal fun NavGraphBuilder.bioScreen(
    onNavigateUp: () -> Unit,
    onApply: () -> Unit
) {
    composable(BioScreenRoutePattern) {
        val viewModel: BioViewModel = hiltViewModel()

        BioScreen(
            uiState = viewModel.uiState,
            uiEvent = viewModel.uiEvent,
            onAction = viewModel::onAction,
            onNavigateUp = onNavigateUp,
            onApply = onApply
        )
    }
}
