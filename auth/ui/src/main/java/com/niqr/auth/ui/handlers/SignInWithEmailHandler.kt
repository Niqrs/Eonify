package com.niqr.auth.ui.handlers

import com.niqr.auth.domain.AuthRepository
import com.niqr.auth.domain.model.SignInWIthEmailResult
import com.niqr.auth.ui.handlers.model.EmailSignInResult
import com.niqr.core_util.Result
import javax.inject.Inject

class SignInWithEmailHandler @Inject constructor(
    private val repo: AuthRepository
) {

    suspend fun signIn(email: String, password: String): EmailSignInResult {
        return when(repo.firebaseSignInWithEmailAndPassword(email, password)) {
            SignInWIthEmailResult.Success -> Result.Success(Unit)
            SignInWIthEmailResult.InvalidCredentials -> Result.Error("Invalid email or password")
            SignInWIthEmailResult.InvalidUser -> Result.Error("User has been disabled or deleted")
            SignInWIthEmailResult.UnknownException -> Result.Error("Something went wrong")
        }
    }
}