package com.niqr.auth.ui.screens.signup

sealed class SignupUiEvent {
    object NavigateToSignin: SignupUiEvent()
    data class ShowSnackbar(val message: String): SignupUiEvent()
}
