package com.niqr.splash.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.niqr.splash.ui.screens.entry.EntryScreenRoutePattern
import com.niqr.splash.ui.screens.entry.entryScreen

const val GreetingGraphRoutePattern = "greeting"

fun NavController.navigateToGreetingGraph() {
    this.navigate(GreetingGraphRoutePattern) {
        popUpTo(0)
        launchSingleTop = true
    }
}

fun NavGraphBuilder.greetingGraph(
    navController: NavController,
    onNavigateNext: () -> Unit
) {
    navigation(
        route = GreetingGraphRoutePattern,
        startDestination = EntryScreenRoutePattern
    ) {
        entryScreen(
            onNavigateNext = onNavigateNext
        )
    }
}