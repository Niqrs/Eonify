package com.niqr.auth.ui.screens.forgot

sealed class ForgotUiEvent {
    object NavigateUp: ForgotUiEvent()
    data class ShowSnackbar(val message: String): ForgotUiEvent()
}
