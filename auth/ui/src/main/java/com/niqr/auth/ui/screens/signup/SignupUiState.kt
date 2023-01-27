package com.niqr.auth.ui.screens.signup

data class SignupUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val agreedWithPolicy: Boolean = false
)
