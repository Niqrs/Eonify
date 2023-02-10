package com.niqr.profile.ui.screens.profile.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.niqr.profile.ui.screens.profile.ProfileAction
import com.niqr.profile.ui.screens.profile.ProfileEvent
import kotlinx.coroutines.flow.Flow

@Composable
fun ProfileUiEventHandler(
    uiEvent: Flow<ProfileEvent>,
    onAction: (ProfileAction) -> Unit,
    onSignOut: () -> Unit,
    onOpenBio: () -> Unit
) {
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { /*onAction(ProfileAction.OnSignOut)*/ }
    )

    LaunchedEffect(true) {
        uiEvent.collect {
            when(it) {
                ProfileEvent.SignOut -> onSignOut()
                ProfileEvent.OpenBio -> onOpenBio()
                ProfileEvent.PickPhoto -> {
                    photoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
            }
        }
    }
}