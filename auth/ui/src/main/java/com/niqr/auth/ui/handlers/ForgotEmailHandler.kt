package com.niqr.auth.ui.handlers

import com.niqr.auth.domain.AuthRepository
import com.niqr.auth.domain.model.SendPasswordResetEmailResult
import com.niqr.auth.ui.handlers.model.EmailForgotResult
import com.niqr.core_util.Result
import javax.inject.Inject

class ForgotEmailHandler @Inject constructor(
    private val repo: AuthRepository
) {

    suspend fun sendEmail(email: String): EmailForgotResult {
        return when(repo.firebaseSendPasswordResetEmail(email)) {
            SendPasswordResetEmailResult.Success -> Result.Success(Unit)
            SendPasswordResetEmailResult.InvalidEmail -> Result.Error("The email address is badly formatted")
            SendPasswordResetEmailResult.InvalidUser -> Result.Error("There is no user with this email")
            SendPasswordResetEmailResult.TooManyRequests -> Result.Error("Too many request. Try again later")
            SendPasswordResetEmailResult.UnknownException -> Result.Error("Unknown Exception")
        }
    }
}