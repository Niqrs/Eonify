package com.niqr.auth.ui.screens.signup

import android.content.Intent

sealed class SignupEvent {
    object NavigateToSignin: SignupEvent()
    data class LaunchGoogleAuth(val intent: Intent): SignupEvent()
    object Success: SignupEvent()
    data class ShowSnackbar(val message: String): SignupEvent()
}
