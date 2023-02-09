package com.niqr.profile.data.model

import com.google.firebase.Timestamp
import com.niqr.profile.domain.User

data class FirestoreUser(
    val createdAt: Timestamp? = null,
    val photoUrl: String? = null,
    val displayName: String? = null,
    val email: String? = null,
    val bio: String? = null
)

fun FirestoreUser.toUser() = User(
    photoUrl = photoUrl ?: "",
    displayName = displayName ?: "",
    email = email ?: "",
    bio = bio ?: ""
)