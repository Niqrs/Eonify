package com.niqr.auth.domain

import com.google.firebase.auth.AuthCredential

interface AuthRepository {
    val isAuthenticated: Boolean

    suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential): Boolean
}