package com.niqr.auth.data

import android.util.Log
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FieldValue.serverTimestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.niqr.auth.domain.AuthRepository
import com.niqr.auth.domain.model.SendPasswordResetEmailResult
import com.niqr.auth.domain.model.SignInWIthEmailResult
import com.niqr.auth.domain.model.SignUpWithEmailResult
import com.niqr.core.FirebaseConstants.CREATED_AT
import com.niqr.core.FirebaseConstants.DISPLAY_NAME
import com.niqr.core.FirebaseConstants.EMAIL
import com.niqr.core.FirebaseConstants.PHOTO_URL
import com.niqr.core.FirebaseConstants.USERS
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) : AuthRepository {
    override val isAuthenticated = auth.currentUser != null

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

    override suspend fun firebaseSignInWithEmailAndPassword(
        email: String,
        password: String
    ): SignInWIthEmailResult {
        return try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
            if (isNewUser) {
                createUserInFirestore()
            }
            SignInWIthEmailResult.Success
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            SignInWIthEmailResult.InvalidCredentials
        } catch (e: FirebaseAuthInvalidUserException) {
            SignInWIthEmailResult.InvalidUser
        } catch (e: FirebaseTooManyRequestsException) {
            SignInWIthEmailResult.TooManyRequests
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
            SignInWIthEmailResult.UnknownException
        }
    }

    override suspend fun firebaseSignUpWithEmailAndPassword(
        name: String,
        email: String,
        password: String
    ): SignUpWithEmailResult {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
            if (isNewUser) {
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(name).build()
                auth.currentUser?.updateProfile(profileUpdates)?.await()
                createUserInFirestore()
                auth.currentUser?.sendEmailVerification()
            }
            SignUpWithEmailResult.Success
        } catch (e: FirebaseAuthWeakPasswordException) {
            SignUpWithEmailResult.WeakPassword
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            SignUpWithEmailResult.InvalidCredentials
        } catch (e: FirebaseAuthUserCollisionException) {
            SignUpWithEmailResult.UserCollision
        } catch (e: FirebaseTooManyRequestsException) {
            SignUpWithEmailResult.TooManyRequests
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
            SignUpWithEmailResult.UnknownException
        }
    }

    override suspend fun firebaseSendPasswordResetEmail(email: String): SendPasswordResetEmailResult {
        return try {
            auth.sendPasswordResetEmail(email).await()
            SendPasswordResetEmailResult.Success
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            Log.e(TAG, e.message.toString())
            SendPasswordResetEmailResult.InvalidEmail
        } catch (e: FirebaseAuthInvalidUserException) {
            Log.e(TAG, e.message.toString())
            SendPasswordResetEmailResult.InvalidUser
        } catch (e: FirebaseTooManyRequestsException) {
            SendPasswordResetEmailResult.TooManyRequests
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
            SendPasswordResetEmailResult.UnknownException
        }
    }

    private suspend fun createUserInFirestore() {
        auth.currentUser?.apply {
            val user = toUser().toMutableMap()
            db.collection(USERS).document(uid).set(user).await()
        }
    }
}

private fun FirebaseUser.toUser() = mapOf(
    CREATED_AT to serverTimestamp(),
    DISPLAY_NAME to displayName,
    EMAIL to email,
    PHOTO_URL to photoUrl?.toString()
)

private const val TAG = "AuthRepository"