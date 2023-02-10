package com.niqr.profile.ui.screens.profile

sealed class ProfileAction {
    object OnSignOut: ProfileAction()
    object OnOpenBio: ProfileAction()
    object OnPickPhoto: ProfileAction()
}