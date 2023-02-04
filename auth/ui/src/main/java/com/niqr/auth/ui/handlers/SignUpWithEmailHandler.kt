package com.niqr.auth.ui.handlers

import com.niqr.auth.domain.AuthRepository
import com.niqr.auth.domain.model.SignUpWithEmailResult
import com.niqr.auth.ui.handlers.model.EmailSignUpResult
import com.niqr.core_util.Result
import javax.inject.Inject

class SignUpWithEmailHandler @Inject constructor(
    private val repo: AuthRepository
) {

    suspend fun signUp(email: String, password: String): EmailSignUpResult {
        return when(repo.firebaseSignUpWithEmailAndPassword(email, password)) {
            SignUpWithEmailResult.Success -> Result.Success(Unit)
            SignUpWithEmailResult.WeakPassword -> Result.Error("Weak password")
            SignUpWithEmailResult.InvalidCredentials -> Result.Error("Invalid email")
            SignUpWithEmailResult.UserCollision -> Result.Error("User with this email already exist")
            SignUpWithEmailResult.TooManyRequests -> Result.Error("Too many request. Try again later")
            SignUpWithEmailResult.UnknownException -> Result.Error("Something went wrong")
        }
    }
}