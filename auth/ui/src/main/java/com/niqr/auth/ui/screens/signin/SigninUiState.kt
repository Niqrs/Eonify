package com.niqr.auth.ui.screens.signin

data class SigninUiState(
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val isLoading: Boolean = false
)