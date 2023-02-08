package com.niqr.profile.ui.screens.profile

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val ProfileScreenRoutePattern = "profileScreen"

internal fun NavController.navigateToProfileScreen() {
    this.navigate(ProfileScreenRoutePattern) {
        popUpTo(0)
        launchSingleTop = true
    }
}

internal fun NavGraphBuilder.profileScreen(onSignOut: () -> Unit, onOpenBio: () -> Unit) {
    composable(ProfileScreenRoutePattern) {
        val viewModel: ProfileViewModel = hiltViewModel()

        ProfileScreen(
            uiState = viewModel.uiState,
            uiEvent = viewModel.uiEvent,
            onAction = viewModel::onAction,
            onSignOut = onSignOut,
            onOpenBio = onOpenBio
        )
    }
}