package com.niqr.auth.ui.screens.signin

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val SigninScreenRoutePattern = "signin"

internal fun NavController.navigateToSignin() {
    this.navigate(SigninScreenRoutePattern)
}

internal fun NavGraphBuilder.signinScreen(
    onNavigateToForgot: () -> Unit,
    onNavigateToSignup: () -> Unit
) {
    composable(SigninScreenRoutePattern) {
        val viewModel: SigninViewModel = hiltViewModel()
        SigninScreen(
            uiState = viewModel.uiState,
            uiEvent = viewModel.uiEvent,
            onAction = viewModel::onAction,
            onNavigateToForgot = onNavigateToForgot,
            onNavigateToSignup = onNavigateToSignup
        )
    }
}