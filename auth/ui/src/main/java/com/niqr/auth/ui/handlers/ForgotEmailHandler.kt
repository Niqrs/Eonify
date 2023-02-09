package com.niqr.auth.ui.handlers

import arrow.core.Either
import com.niqr.auth.domain.AuthRepository
import com.niqr.auth.domain.model.SendPasswordResetEmailResult
import com.niqr.auth.ui.handlers.model.EmailForgotResult
import javax.inject.Inject

class ForgotEmailHandler @Inject constructor(
    private val repo: AuthRepository
) {

    suspend fun sendEmail(email: String): EmailForgotResult {
        return when(repo.firebaseSendPasswordResetEmail(email)) {
            SendPasswordResetEmailResult.Success -> Either.Right(Unit)
            SendPasswordResetEmailResult.InvalidEmail -> Either.Left("The email address is badly formatted")
            SendPasswordResetEmailResult.InvalidUser -> Either.Left("There is no user with this email")
            SendPasswordResetEmailResult.TooManyRequests -> Either.Left("Too many request. Try again later")
            SendPasswordResetEmailResult.UnknownException -> Either.Left("Unknown Exception")
        }
    }
}