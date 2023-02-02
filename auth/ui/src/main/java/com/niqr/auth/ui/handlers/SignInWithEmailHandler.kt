package com.niqr.auth.ui.handlers

import com.niqr.auth.domain.AuthRepository
import com.niqr.auth.domain.model.SignInWIthEmailResult
import javax.inject.Inject

class SignInWithEmailHandler @Inject constructor(
    private val repo: AuthRepository
) {

    suspend fun signIn(email: String, password: String): SignInWIthEmailResult {
        return repo.firebaseSignInWithEmailAndPassword(email, password)
    }
}