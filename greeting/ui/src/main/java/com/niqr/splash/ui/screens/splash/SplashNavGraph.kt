package com.niqr.splash.ui.screens.splash

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val SplashScreenRoutePattern = "splash"

internal fun NavController.navigateToSplash() {
    this.navigate(SplashScreenRoutePattern) {
        popUpTo(0)
        launchSingleTop = true
    }
}

internal fun NavGraphBuilder.splashScreen(
    onSplashEnd: () -> Unit
) {
    composable(
        route = SplashScreenRoutePattern
    ) {
        val viewModel: SplashViewModel = hiltViewModel()
        SplashScreen(
            uiEvent = viewModel.uiEvent,
            onEvent = viewModel::onEvent,
            onSplashEnd = onSplashEnd
        )
    }
}