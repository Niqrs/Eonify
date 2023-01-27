package com.niqr.auth.ui.screens.forgot

sealed class ForgotEvent {
    object OnNavigateUp: ForgotEvent()
    data class OnEmailChange(val email: String): ForgotEvent()
    object OnContinueClick: ForgotEvent()
}