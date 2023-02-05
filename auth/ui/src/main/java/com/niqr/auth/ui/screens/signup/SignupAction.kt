package com.niqr.auth.ui.screens.signup

import androidx.activity.result.ActivityResult

sealed class SignupAction {
    object OnNavigateToSignin: SignupAction()
    data class OnFirstNameChange(val name: String): SignupAction()
    data class OnOptionalNameChange(val name: String): SignupAction()
    data class OnEmailChange(val email: String): SignupAction()
    data class OnPasswordChange(val password: String): SignupAction()
    data class OnPasswordVisibilityChange(val visible: Boolean): SignupAction()
    data class OnAgreeWIthPolicyChange(val checked: Boolean): SignupAction()
    object OnTermsAndPolicyClick: SignupAction()
    object OnSignupWithFacebook: SignupAction()
    object OnSignupWithGoogle: SignupAction()
    object OnCreateAccountClick: SignupAction()
    data class OnSignupWithGoogleResult(val result: ActivityResult): SignupAction()
}