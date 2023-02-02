package com.niqr.auth.ui.handlers.model

sealed class GoogleAuthResult {
    object Success: GoogleAuthResult()
    object Canceled: GoogleAuthResult()
    object UnknownException: GoogleAuthResult()
}
