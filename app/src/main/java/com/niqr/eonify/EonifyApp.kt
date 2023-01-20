package com.niqr.eonify

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.niqr.auth.ui.AuthGraphRoutePattern
import com.niqr.auth.ui.authGraph

@Composable
fun EonifyApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AuthGraphRoutePattern
    ) {
        authGraph(navController)
    }
}