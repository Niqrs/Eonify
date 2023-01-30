package com.niqr.auth.ui.screens.signin

sealed class SigninEvent {
    object NavigateToForgot: SigninEvent()
    object NavigateToSignup: SigninEvent()
    data class ShowSnackbar(val message: String): SigninEvent()
}
