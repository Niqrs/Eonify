package com.niqr.eonify

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.niqr.auth.ui.authGraph
import com.niqr.auth.ui.navigateToAuthGraph
import com.niqr.core_ui.theme.EonifyTheme
import com.niqr.splash.ui.GreetingGraphRoutePattern
import com.niqr.splash.ui.greetingGraph

@Composable
fun EonifyApp() {
    val navController = rememberNavController()

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = EonifyTheme.colorScheme.background
    ) {
        NavHost(
            modifier = Modifier
                .background(EonifyTheme.colorScheme.background),
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
}