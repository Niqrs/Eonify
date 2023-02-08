package com.niqr.profile.domain

import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    val user: User
    fun userFlow(): Flow<User?>
    fun saveBio(bio: String)
    suspend fun signOut(): Boolean
}