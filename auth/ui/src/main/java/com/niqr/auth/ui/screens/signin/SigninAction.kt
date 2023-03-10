package com.niqr.auth.ui.screens.signin

import androidx.activity.result.ActivityResult

sealed class SigninAction {
    object OnNavigateToForgot: SigninAction()
    object OnNavigateToSignup: SigninAction()
    data class OnEmailChange(val email: String): SigninAction()
    data class OnPasswordChange(val password: String): SigninAction()
    data class OnPasswordVisibilityChange(val visible: Boolean): SigninAction()
    object OnSignupWithFacebook: SigninAction()
    object OnSignupWithGoogle: SigninAction()
    data class OnSignupWithGoogleResult(val result: ActivityResult): SigninAction()
    object OnLoginClick: SigninAction()
}
