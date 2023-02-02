package com.niqr.auth.domain.model

sealed class SignUpWithEmailResult {
    object Success: SignUpWithEmailResult()
    object WeakPassword: SignUpWithEmailResult()
    object InvalidCredentials: SignUpWithEmailResult()
    object UserCollision: SignUpWithEmailResult()
    object UnknownException: SignUpWithEmailResult()
}