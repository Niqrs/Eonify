package com.niqr.auth.ui.screens.signup

import androidx.activity.result.IntentSenderRequest

sealed class SignupEvent {
    object NavigateToSignin: SignupEvent()
    data class LaunchGoogleAuth(val intent: IntentSenderRequest): SignupEvent()
    object Success: SignupEvent()
    data class ShowSnackbar(val message: String): SignupEvent()
}
