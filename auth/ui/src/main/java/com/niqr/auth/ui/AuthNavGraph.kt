package com.niqr.auth.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.niqr.auth.ui.screens.forgot.forgotScreen
import com.niqr.auth.ui.screens.forgot.navigateToForgot
import com.niqr.auth.ui.screens.signin.navigateToSignin
import com.niqr.auth.ui.screens.signin.signinScreen
import com.niqr.auth.ui.screens.signup.SignupScreenRoutePattern
import com.niqr.auth.ui.screens.signup.signupScreen

const val AuthGraphRoutePattern = "auth"

fun NavController.navigateToAuthGraph() {
    this.navigate(AuthGraphRoutePattern) {
        popUpTo(0)
        launchSingleTop = true
    }
}

fun NavGraphBuilder.authGraph(
    navController: NavController,
    onSuccessAuth: () -> Unit
) {
    navigation(
        startDestination = SignupScreenRoutePattern,
        route = AuthGraphRoutePattern
    ) {
        forgotScreen(
            onNavigateBack = navController::navigateUp
        )
        signinScreen(
            onNavigateToForgot = navController::navigateToForgot,
            onNavigateToSignup = navController::navigateUp,
            onSuccessAuth = onSuccessAuth
        )
        signupScreen(
            onNavigateToSignin = navController::navigateToSignin,
            onSuccessAuth = onSuccessAuth
        )
    }
}