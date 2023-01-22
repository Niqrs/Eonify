package com.niqr.splash.ui.screens.splash

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val SplashGraphRoutePattern = "splash"

internal fun NavController.navigateToSplash() {
    this.navigate(SplashGraphRoutePattern) {
        popUpTo(0)
        launchSingleTop = true
    }
}

internal fun NavGraphBuilder.splashScreen(
    onSplashEnd: () -> Unit
) {
    composable(
        route = SplashGraphRoutePattern
    ) {
        val viewModel: SplashViewModel = hiltViewModel()
        SplashScreen(
            uiEvent = viewModel.uiEvent,
            onEvent = viewModel::onEvent,
            onSplashEnd = onSplashEnd
        )
    }
}