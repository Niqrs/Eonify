package com.niqr.auth.domain.model

sealed class SendPasswordResetEmailResult {
    object Success: SendPasswordResetEmailResult()
    object InvalidEmail: SendPasswordResetEmailResult()
    object InvalidUser: SendPasswordResetEmailResult()
    object TooManyRequests: SendPasswordResetEmailResult()
    object UnknownException: SendPasswordResetEmailResult()
}
