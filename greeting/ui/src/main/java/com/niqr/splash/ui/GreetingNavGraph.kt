package com.niqr.splash.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.niqr.splash.ui.screens.entry.entryScreen
import com.niqr.splash.ui.screens.entry.navigateToEntry
import com.niqr.splash.ui.screens.splash.SplashScreenRoutePattern
import com.niqr.splash.ui.screens.splash.splashScreen

const val GreetingGraphRoutePattern = "greeting"

fun NavController.navigateToGreetingGraph() {
    this.navigate(GreetingGraphRoutePattern) {
        popUpTo(0)
        launchSingleTop = true
    }
}

fun NavGraphBuilder.greetingGraph(
    navController: NavController,
    onNavigateNext: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    navigation(
        route = GreetingGraphRoutePattern,
        startDestination = SplashScreenRoutePattern
    ) {
        splashScreen(
            onNavigateToEntry = navController::navigateToEntry,
            onNavigateToProfile = onNavigateToProfile
        )
        entryScreen(
            onNavigateNext = onNavigateNext
        )
    }
}