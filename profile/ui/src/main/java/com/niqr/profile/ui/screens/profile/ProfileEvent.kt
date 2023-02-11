package com.niqr.profile.ui.screens.profile

sealed class ProfileEvent {
    object SignOut: ProfileEvent()
    object OpenBio: ProfileEvent()
    object OpenEditName: ProfileEvent()
    object PickImage: ProfileEvent()
}