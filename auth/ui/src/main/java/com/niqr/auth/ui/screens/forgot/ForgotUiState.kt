package com.niqr.auth.ui.screens.forgot

data class ForgotUiState(
    val email: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false
)
