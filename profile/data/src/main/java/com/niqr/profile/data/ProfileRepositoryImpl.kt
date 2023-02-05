package com.niqr.profile.data

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.niqr.core.AppConstants.NO_VALUE
import com.niqr.profile.domain.ProfileRepository
import com.niqr.profile.domain.User
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
//    private val firestore: Firest,
    private var googleClient: GoogleSignInClient,
): ProfileRepository {
    override val user = User(
        uid = auth.currentUser?.uid ?: NO_VALUE,
        photoUrl = auth.currentUser?.photoUrl.toString(),
        displayName = auth.currentUser?.displayName ?: NO_VALUE,
        email = auth.currentUser?.email ?: NO_VALUE,
        bio = ""
    )

    override suspend fun signOut(): Boolean {
        return try {
            googleClient.signOut().await()
            auth.signOut()
            true // Success
        } catch (e: Exception) {
            false // Failure
        }
    }
}