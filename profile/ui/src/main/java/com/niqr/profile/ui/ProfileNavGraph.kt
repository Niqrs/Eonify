package com.niqr.profile.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.niqr.profile.ui.screens.bio.bioScreen
import com.niqr.profile.ui.screens.bio.navigateToBioScreen
import com.niqr.profile.ui.screens.editName.editNameScreen
import com.niqr.profile.ui.screens.editName.navigateToEditNameScreen
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
            onSignOut = onSignOut,
            onOpenBio = navController::navigateToBioScreen,
            onOpenEditName = navController::navigateToEditNameScreen
        )
        bioScreen(
            onNavigateUp = navController::navigateUp,
            onApply = navController::navigateUp
        )
        editNameScreen(
            onNavigateUp = navController::navigateUp,
            onApply = navController::navigateUp
        )
    }
}