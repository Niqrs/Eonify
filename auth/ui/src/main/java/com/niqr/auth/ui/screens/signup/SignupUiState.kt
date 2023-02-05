package com.niqr.auth.ui.screens.signup

data class SignupUiState(
    val firstName: String = "",
    val optionalName: String = "",
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val agreedWithPolicy: Boolean = false,
    val isLoading: Boolean = false
)