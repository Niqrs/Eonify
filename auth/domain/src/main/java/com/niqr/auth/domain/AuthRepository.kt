package com.niqr.auth.domain

import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.firebase.auth.AuthCredential

interface AuthRepository {
    val isAuthenticated: Boolean

    suspend fun oneTapSignInWithGoogle(): BeginSignInResult?

    suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential): Boolean
}