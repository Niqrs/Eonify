package com.niqr.auth.ui.screens.forgot

sealed class ForgotEvent {
    object NavigateUp: ForgotEvent()
    data class ShowSnackbar(val message: String): ForgotEvent()
}
