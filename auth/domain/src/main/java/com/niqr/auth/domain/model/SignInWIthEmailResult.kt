package com.niqr.auth.domain.model

sealed class SignInWIthEmailResult {
    object Success: SignInWIthEmailResult()
    object InvalidCredentials: SignInWIthEmailResult()
    object UnknownException: SignInWIthEmailResult()
}