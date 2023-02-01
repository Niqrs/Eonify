package com.niqr.profile.data

import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.niqr.core.AppConstants.NO_VALUE
import com.niqr.profile.domain.ProfileRepository
import com.niqr.profile.domain.User
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private var oneTapClient: SignInClient,
): ProfileRepository {
    override val user = User(
        uid = auth.currentUser?.uid ?: NO_VALUE,
        photoUrl = auth.currentUser?.photoUrl.toString(),
        displayName = auth.currentUser?.displayName ?: NO_VALUE,
        email = auth.currentUser?.email ?: NO_VALUE
    )

    override suspend fun signOut(): Boolean {
        return try {
            oneTapClient.signOut().await()
            auth.signOut()
            true // Success
        } catch (e: Exception) {
            false // Failure
        }
    }
}