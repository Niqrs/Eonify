package com.niqr.profile.domain

interface ProfileRepository {
    val user: User

    suspend fun signOut(): Boolean
}