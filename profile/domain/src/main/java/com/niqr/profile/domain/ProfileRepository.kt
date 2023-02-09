package com.niqr.profile.domain

import arrow.core.Option
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    val user: User
    fun userFlow(): Flow<Option<User>>
    fun saveBio(bio: String)
    suspend fun signOut(): Boolean
}