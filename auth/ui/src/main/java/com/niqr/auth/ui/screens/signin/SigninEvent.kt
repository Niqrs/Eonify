package com.niqr.auth.ui.screens.signin

sealed class SigninEvent {
    object OnNavigateToForgot: SigninEvent()
    object OnNavigateToSignup: SigninEvent()
    data class OnEmailChange(val email: String): SigninEvent()
    data class OnPasswordChange(val password: String): SigninEvent()
    data class OnPasswordVisibilityChange(val visible: Boolean): SigninEvent()
    object OnSignupWithFacebook: SigninEvent()
    object OnSignupWithGoogle: SigninEvent()
    object OnLoginClick: SigninEvent()
}
