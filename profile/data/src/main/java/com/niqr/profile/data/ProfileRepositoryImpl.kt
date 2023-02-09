package com.niqr.profile.data

import arrow.core.getOrElse
import arrow.core.none
import arrow.core.some
import arrow.core.toOption
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.niqr.core.AppConstants.NO_VALUE
import com.niqr.core.FirebaseConstants.BIO
import com.niqr.core.FirebaseConstants.USERS
import com.niqr.profile.data.model.FirestoreUser
import com.niqr.profile.data.model.toUser
import com.niqr.profile.domain.ProfileRepository
import com.niqr.profile.domain.User
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private var googleClient: GoogleSignInClient
): ProfileRepository {
    private val uid = auth.currentUser?.uid?.some() ?: none()
    private val userRef = firestore.collection(USERS).document(uid.getOrElse { "" })

    override val user = User(
        photoUrl = auth.currentUser?.photoUrl.toString(),
        displayName = auth.currentUser?.displayName ?: NO_VALUE,
        email = auth.currentUser?.email ?: NO_VALUE,
        bio = ""
    )

    override fun userFlow() = userRef.snapshots().map {
        it.toObject(FirestoreUser::class.java)?.toUser().toOption()
    }

    override fun saveBio(bio: String) {
        userRef.update(BIO, bio)
    }

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