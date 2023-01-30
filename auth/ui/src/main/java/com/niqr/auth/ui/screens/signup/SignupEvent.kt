package com.niqr.auth.ui.screens.signup

sealed class SignupEvent {
    object NavigateToSignin: SignupEvent()
    data class ShowSnackbar(val message: String): SignupEvent()
}
