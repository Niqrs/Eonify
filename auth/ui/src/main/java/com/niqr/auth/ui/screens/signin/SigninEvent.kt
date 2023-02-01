package com.niqr.auth.ui.screens.signin

import android.content.Intent

sealed class SigninEvent {
    object NavigateToForgot: SigninEvent()
    object NavigateToSignup: SigninEvent()
    data class LaunchGoogleAuth(val intent: Intent): SigninEvent()
    object Success: SigninEvent()
    data class ShowSnackbar(val message: String): SigninEvent()
}
