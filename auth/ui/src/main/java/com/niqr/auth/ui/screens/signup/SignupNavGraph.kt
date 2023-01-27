package com.niqr.auth.ui.screens.signup

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val SignupGraphRoutePattern = "signup"

internal fun NavController.navigateToSignup() {
    this.navigate(SignupGraphRoutePattern)
}

internal fun NavGraphBuilder.signupScreen(
    onNavigateToSignin: () -> Unit
) {
    composable(
        route = SignupGraphRoutePattern
    ) {
        val viewModel: SignupViewModel = hiltViewModel()
        SignupScreen(
            uiState = viewModel.uiState,
            uiEvent = viewModel.uiEvent,
            onEvent = viewModel::onEvent,
            onNavigateToSignin = onNavigateToSignin
        )
    }
}