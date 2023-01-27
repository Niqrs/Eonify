package com.niqr.auth.ui.screens.signup

sealed class SignupEvent {
    object OnNavigateToSignin: SignupEvent()
    data class OnNameChange(val name: String): SignupEvent()
    data class OnEmailChange(val email: String): SignupEvent()
    data class OnPasswordChange(val password: String): SignupEvent()
    data class OnPasswordVisibilityChange(val visible: Boolean): SignupEvent()
    data class OnAgreeWIthPolicyChange(val checked: Boolean): SignupEvent()
    object OnTermsAndPolicyClick: SignupEvent()
    object OnSignupWithFacebook: SignupEvent()
    object OnSignupWithGoogle: SignupEvent()
    object OnCreateAccountClick: SignupEvent()
}