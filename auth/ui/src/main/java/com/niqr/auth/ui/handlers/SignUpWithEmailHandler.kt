package com.niqr.auth.ui.handlers

import com.niqr.auth.domain.AuthRepository
import com.niqr.auth.domain.model.SignUpWithEmailResult
import javax.inject.Inject

class SignUpWithEmailHandler @Inject constructor(
    private val repo: AuthRepository
) {

    suspend fun signUp(email: String, password: String): SignUpWithEmailResult {
        return repo.firebaseSignUpWithEmailAndPassword(email, password)
    }
}