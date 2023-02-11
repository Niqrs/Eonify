package com.niqr.profile.ui.screens.editName

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val EditNameScreenRoutePattern = "editName"

internal fun NavController.navigateToEditNameScreen() {
    this.navigate(EditNameScreenRoutePattern) {
        launchSingleTop = true
    }
}

internal fun NavGraphBuilder.editNameScreen(
    onNavigateUp: () -> Unit,
    onApply: () -> Unit
) {
    composable(EditNameScreenRoutePattern) {
        val viewModel: EditNameViewModel = hiltViewModel()

        EditNameScreen(
            uiState = viewModel.uiState,
            uiEvent = viewModel.uiEvent,
            onAction = viewModel::onAction,
            onNavigateUp = onNavigateUp,
            onApply = onApply
        )
    }
}