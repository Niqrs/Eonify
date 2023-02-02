package com.niqr.auth.ui.handlers

import android.app.Activity
import android.util.Log
import androidx.activity.result.ActivityResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.niqr.auth.domain.AuthRepository
import com.niqr.auth.ui.handlers.model.GoogleAuthResult
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GoogleAuthResultHandler @Inject constructor(
    private val repo: AuthRepository
) {
    suspend fun handle(result: ActivityResult): GoogleAuthResult { //TODO: Return type should be better
        return when(result.resultCode) {
            Activity.RESULT_OK -> try {
                val googleAccount = GoogleSignIn.getSignedInAccountFromIntent(result.data).await()
                val googleToken = googleAccount.idToken
                val googleCredentials = GoogleAuthProvider.getCredential(googleToken, null)
                val signInWithGoogleResponse = repo.firebaseSignInWithGoogle(googleCredentials)

                if (signInWithGoogleResponse) {
                    GoogleAuthResult.Success
                } else {
                    GoogleAuthResult.UnknownException
                }
            } catch (e: ApiException) {
                Log.e(TAG, e.message.toString())
                GoogleAuthResult.UnknownException
            }
            Activity.RESULT_CANCELED -> {
                Log.w(TAG, "Activity Canceled")
                GoogleAuthResult.Canceled
            }
            else -> {
                Log.e(TAG, "Unknown Exception")
                GoogleAuthResult.UnknownException
            }
        }
    }
}

private const val TAG = "GoogleAuthResultHandler"