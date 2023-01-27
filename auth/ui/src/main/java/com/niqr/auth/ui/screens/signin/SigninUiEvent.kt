package com.niqr.auth.ui.screens.signin

sealed class SigninUiEvent {
    object NavigateToForgot: SigninUiEvent()
    object NavigateToSignup: SigninUiEvent()
    data class ShowSnackbar(val message: String): SigninUiEvent()
}
