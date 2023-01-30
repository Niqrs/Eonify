package com.niqr.auth.ui.screens.signup

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val SignupScreenRoutePattern = "signup"

internal fun NavController.navigateToSignup() {
    this.navigate(SignupScreenRoutePattern)
}

internal fun NavGraphBuilder.signupScreen(
    onNavigateToSignin: () -> Unit
) {
    composable(
        route = SignupScreenRoutePattern
    ) {
        val viewModel: SignupViewModel = hiltViewModel()
        SignupScreen(
            uiState = viewModel.uiState,
            uiEvent = viewModel.uiEvent,
            onAction = viewModel::onAction,
            onNavigateToSignin = onNavigateToSignin
        )
    }
}