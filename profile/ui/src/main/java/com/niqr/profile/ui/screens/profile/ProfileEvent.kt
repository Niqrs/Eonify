package com.niqr.profile.ui.screens.profile

sealed class ProfileEvent {
    object SignOut: ProfileEvent()
    object OpenBio: ProfileEvent()
    object PickImage: ProfileEvent()
}