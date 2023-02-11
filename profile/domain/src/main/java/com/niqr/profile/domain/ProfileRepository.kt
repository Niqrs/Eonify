package com.niqr.profile.domain

import android.net.Uri
import arrow.core.Option
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    val user: User
    fun userFlow(): Flow<Option<User>>
    fun updatePhoto(image: Uri)
    fun saveBio(bio: String)
    fun saveUsername(name: String)
    suspend fun signOut(): Boolean
}