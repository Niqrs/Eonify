package com.niqr.eonify

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.niqr.auth.ui.authGraph
import com.niqr.auth.ui.navigateToAuthGraph
import com.niqr.splash.ui.GreetingGraphRoutePattern
import com.niqr.splash.ui.greetingGraph

@Composable
fun EonifyApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = GreetingGraphRoutePattern
    ) {
        greetingGraph(
            navController = navController,
            onNavigateNext = navController::navigateToAuthGraph
        )
        authGraph(navController)
    }
}