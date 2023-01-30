package com.niqr.auth.ui.screens.forgot

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ForgotScreenRoutePattern = "forgot"
internal fun NavController.navigateToForgot() {
    this.navigate(ForgotScreenRoutePattern)
}

internal fun NavGraphBuilder.forgotScreen(
    onNavigateBack: () -> Unit
) {
    composable(ForgotScreenRoutePattern) {
        val viewModel: ForgotViewModel = hiltViewModel()

        ForgotScreen(
            uiState = viewModel.uiState,
            uiEvent = viewModel.uiEvent,
            onAction = viewModel::onAction,
            onNavigateUp = onNavigateBack
        )
    }
}