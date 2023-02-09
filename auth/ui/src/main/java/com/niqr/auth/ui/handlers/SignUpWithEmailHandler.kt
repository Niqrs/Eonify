package com.niqr.auth.ui.handlers

import arrow.core.Either
import com.niqr.auth.domain.AuthRepository
import com.niqr.auth.domain.model.SignUpWithEmailResult
import com.niqr.auth.ui.handlers.model.EmailSignUpResult
import javax.inject.Inject

class SignUpWithEmailHandler @Inject constructor(
    private val repo: AuthRepository
) {

    suspend fun signUp(name: String, email: String, password: String): EmailSignUpResult {
        return when(repo.firebaseSignUpWithEmailAndPassword(name, email, password)) {
            SignUpWithEmailResult.Success -> Either.Right(Unit)
            SignUpWithEmailResult.WeakPassword -> Either.Left("Weak password")
            SignUpWithEmailResult.InvalidCredentials -> Either.Left("Invalid email")
            SignUpWithEmailResult.UserCollision -> Either.Left("User with this email already exist")
            SignUpWithEmailResult.TooManyRequests -> Either.Left("Too many request. Try again later")
            SignUpWithEmailResult.UnknownException -> Either.Left("Something went wrong")
        }
    }
}