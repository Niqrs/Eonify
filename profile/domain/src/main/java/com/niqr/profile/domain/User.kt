package com.niqr.profile.domain

data class User(
    val uid: String = "",
    val photoUrl: String = "",
    val displayName: String = "",
    val email: String = "",
    val bio: String = ""
)
