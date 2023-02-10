package com.niqr.profile.data

import android.net.Uri
import arrow.core.getOrElse
import arrow.core.none
import arrow.core.some
import arrow.core.toOption
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.storage.FirebaseStorage
import com.niqr.core.AppConstants.NO_VALUE
import com.niqr.core.FirebaseConstants.BIO
import com.niqr.core.FirebaseConstants.PHOTO_URL
import com.niqr.core.FirebaseConstants.USERS
import com.niqr.core.FirebaseConstants.USERS_PHOTOS
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
    private val storage: FirebaseStorage,
    private var googleClient: GoogleSignInClient
): ProfileRepository {
    private val uid = auth.currentUser?.uid?.some() ?: none()
    private val userRef = firestore.collection(USERS).document(uid.getOrElse { "" })
    private val userPhotoRef = storage.reference.child("$USERS_PHOTOS/${uid.getOrElse { "" }}")

    override val user = User(
        photoUrl = auth.currentUser?.photoUrl.toString(),
        displayName = auth.currentUser?.displayName ?: NO_VALUE,
        email = auth.currentUser?.email ?: NO_VALUE,
        bio = ""
    )

    override fun userFlow() = userRef.snapshots().map {
        it.toObject(FirestoreUser::class.java)?.toUser().toOption()
    }

    override fun updatePhoto(image: Uri) {
        userPhotoRef.putFile(image).onSuccessTask {
            userPhotoRef.downloadUrl.addOnSuccessListener {
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setPhotoUri(it).build()
                auth.currentUser?.updateProfile(profileUpdates)
                userRef.update(PHOTO_URL, it)
            }
        }
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