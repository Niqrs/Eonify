package com.niqr.auth.ui.screens.signin

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val SigninGraphRoutePattern = "signin"

internal fun NavController.navigateToSignin() {
    this.navigate(SigninGraphRoutePattern)
}

internal fun NavGraphBuilder.signinScreen(
    onNavigateToForgot: () -> Unit,
    onNavigateToSignup: () -> Unit
) {
    composable(SigninGraphRoutePattern) {
        val uiState = SigninUiState()
        SigninScreen(
            uiState = uiState,
            onNavigateToForgot = onNavigateToForgot,
            onNavigateToSignup = onNavigateToSignup
        )
    }
}