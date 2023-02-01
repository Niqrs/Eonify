package com.niqr.auth.ui.handlers

import android.app.Activity
import androidx.activity.result.ActivityResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.niqr.auth.domain.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GoogleAuthResultHandler @Inject constructor(
    private val repo: AuthRepository
) {
    suspend fun handle(result: ActivityResult): Boolean {
        return if (result.resultCode == Activity.RESULT_OK) {
            try {
                val googleAccount = GoogleSignIn.getSignedInAccountFromIntent(result.data).await()
                val googleToken = googleAccount.idToken
                val googleCredentials = GoogleAuthProvider.getCredential(googleToken, null)
                val signInWithGoogleResponse = repo.firebaseSignInWithGoogle(googleCredentials)

                signInWithGoogleResponse
            } catch (_: ApiException) {
                false
            }
        } else {
            false
        }
    }
}