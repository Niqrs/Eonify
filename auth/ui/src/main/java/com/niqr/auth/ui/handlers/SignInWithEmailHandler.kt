package com.niqr.auth.ui.handlers

import arrow.core.Either
import com.niqr.auth.domain.AuthRepository
import com.niqr.auth.domain.model.SignInWIthEmailResult
import com.niqr.auth.ui.handlers.model.EmailSignInResult
import javax.inject.Inject

class SignInWithEmailHandler @Inject constructor(
    private val repo: AuthRepository
) {

    suspend fun signIn(email: String, password: String): EmailSignInResult {
        return when(repo.firebaseSignInWithEmailAndPassword(email, password)) {
            SignInWIthEmailResult.Success -> Either.Right(Unit)
            SignInWIthEmailResult.InvalidCredentials -> Either.Left("Invalid email or password")
            SignInWIthEmailResult.InvalidUser -> Either.Left("User has been disabled or deleted")
            SignInWIthEmailResult.TooManyRequests -> Either.Left("Too many request. Try again later")
            SignInWIthEmailResult.UnknownException -> Either.Left("Something went wrong")
        }
    }
}