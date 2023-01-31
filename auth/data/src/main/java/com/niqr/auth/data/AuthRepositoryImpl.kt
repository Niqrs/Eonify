package com.niqr.auth.data

import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FieldValue.serverTimestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.niqr.auth.domain.AuthRepository
import com.niqr.core.FirebaseConstants.CREATED_AT
import com.niqr.core.FirebaseConstants.DISPLAY_NAME
import com.niqr.core.FirebaseConstants.EMAIL
import com.niqr.core.FirebaseConstants.PHOTO_URL
import com.niqr.core.FirebaseConstants.USERS
import com.niqr.core.HiltConstants.SIGN_IN_REQUEST
import com.niqr.core.HiltConstants.SIGN_UP_REQUEST
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private var oneTapClient: SignInClient,
    @Named(SIGN_IN_REQUEST)
    private var signInRequest: BeginSignInRequest,
    @Named(SIGN_UP_REQUEST)
    private var signUpRequest: BeginSignInRequest,
    private val db: FirebaseFirestore
) : AuthRepository {
    override val isAuthenticated = auth.currentUser != null

    override suspend fun oneTapSignInWithGoogle(): BeginSignInResult? {
        return try {
            val signInResult = oneTapClient.beginSignIn(signInRequest).await()
            signInResult
        } catch (e: Exception) {
            try {
                val signUpResult = oneTapClient.beginSignIn(signUpRequest).await()
                signUpResult
            } catch (e: Exception) {
                null
            }
        }
    }

    override suspend fun firebaseSignInWithGoogle(
        googleCredential: AuthCredential
    ): Boolean {
        return try {
            val authResult = auth.signInWithCredential(googleCredential).await()
            val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
            if (isNewUser) {
                createUserInFirestore()
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    private suspend fun createUserInFirestore() {
        auth.currentUser?.apply {
            val user = toUser()
            db.collection(USERS).document(uid).set(user).await()
        }
    }
}

fun FirebaseUser.toUser() = mapOf(
    CREATED_AT to serverTimestamp(),
    DISPLAY_NAME to displayName,
    EMAIL to email,
    PHOTO_URL to photoUrl?.toString()
)