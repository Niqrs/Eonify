package com.niqr.profile.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.niqr.profile.ui.screens.profile.ProfileScreenRoutePattern
import com.niqr.profile.ui.screens.profile.profileScreen

const val ProfileGraphRoutePattern = "profile"

fun NavController.navigateToProfileGraph() {
    this.navigate(ProfileGraphRoutePattern) {
        popUpTo(0)
        launchSingleTop = true
    }
}

fun NavGraphBuilder.profileGraph(
    navController: NavController,
    onSignOut: () -> Unit
) {
    navigation(
        startDestination = ProfileScreenRoutePattern,
        route = ProfileGraphRoutePattern
    ) {
        profileScreen(
            onSignOut = onSignOut
        )
    }
}