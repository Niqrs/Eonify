package com.niqr.profile.ui.screens.editName

import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val EditNameScreenRoutePattern = "editName"

internal fun NavController.navigateToEditNameScreen() {
    this.navigate(EditNameScreenRoutePattern) {
        launchSingleTop = true
    }
}

internal fun NavGraphBuilder.editNameScreen() {
    composable(EditNameScreenRoutePattern) {
        Text(text = "Edit Name")
    }
}