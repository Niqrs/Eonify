package com.niqr.profile.ui.screens.profile

import android.net.Uri
import arrow.core.Option

sealed class ProfileAction {
    object OnSignOut: ProfileAction()
    object OnOpenBio: ProfileAction()
    object OnEditName: ProfileAction()
    object OnExpandMenu: ProfileAction()
    object OnMenuDismiss: ProfileAction()
    object OnPickImage: ProfileAction()
    data class OnPickImageResult(val uri: Option<Uri>) : ProfileAction()
}