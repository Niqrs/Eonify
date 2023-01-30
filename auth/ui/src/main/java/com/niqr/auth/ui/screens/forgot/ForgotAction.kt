package com.niqr.auth.ui.screens.forgot

sealed class ForgotAction {
    object OnNavigateUp: ForgotAction()
    data class OnEmailChange(val email: String): ForgotAction()
    object OnContinueClick: ForgotAction()
}