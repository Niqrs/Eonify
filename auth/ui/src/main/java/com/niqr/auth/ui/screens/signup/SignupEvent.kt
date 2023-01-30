package com.niqr.auth.ui.screens.signup

sealed class SignupEvent {
    object NavigateToSignin: SignupEvent()
    object Success: SignupEvent()
    data class ShowSnackbar(val message: String): SignupEvent()
}
