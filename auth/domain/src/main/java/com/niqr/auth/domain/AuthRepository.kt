package com.niqr.auth.domain

import com.google.firebase.auth.AuthCredential
import com.niqr.auth.domain.model.SendPasswordResetEmailResult
import com.niqr.auth.domain.model.SignInWIthEmailResult
import com.niqr.auth.domain.model.SignUpWithEmailResult

interface AuthRepository {
    val isAuthenticated: Boolean

    suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential): Boolean
    suspend fun firebaseSignInWithEmailAndPassword(email: String, password: String): SignInWIthEmailResult
    suspend fun firebaseSignUpWithEmailAndPassword(email: String, password: String): SignUpWithEmailResult
    suspend fun firebaseSendPasswordResetEmail(email: String): SendPasswordResetEmailResult
}