package com.niqr.auth.ui.screens.forgot

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ForgotGraphRoutePattern = "forgot"
internal fun NavController.navigateToForgot() {
    this.navigate(ForgotGraphRoutePattern)
}

internal fun NavGraphBuilder.forgotScreen(
    onNavigateBack: () -> Unit
) {
    composable(ForgotGraphRoutePattern) {
        val uiState = ForgotUiState()
        ForgotScreen(
            uiState = uiState,
            onNavigateBack = onNavigateBack
        )
    }
}