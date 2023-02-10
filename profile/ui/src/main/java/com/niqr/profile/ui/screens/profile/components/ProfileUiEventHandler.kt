package com.niqr.profile.ui.screens.profile.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.niqr.profile.ui.screens.profile.ProfileEvent
import kotlinx.coroutines.flow.Flow

@Composable
fun ProfileUiEventHandler(
    uiEvent: Flow<ProfileEvent>,
    onSignOut: () -> Unit,
    onOpenBio: () -> Unit
) {
    LaunchedEffect(true) {
        uiEvent.collect {
            when(it) {
                ProfileEvent.SignOut -> onSignOut()
                ProfileEvent.OpenBio -> onOpenBio()
            }
        }
    }
}