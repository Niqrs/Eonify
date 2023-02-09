package com.niqr.profile.ui.screens.profile

import com.niqr.profile.domain.User

data class ProfileUiState(
    val user: UserUiState = UserUiState()
)

data class UserUiState(
    val photoUrl: String = "",
    val displayName: String = "",
    val email: String = "",
    val bio: String = ""
)

fun User.toUiState() =
    UserUiState(
        photoUrl = this.photoUrl ?: "",
        displayName = this.displayName ?: "",
        email = this.email ?: "",
        bio = this.bio?.ifBlank { "Bio" }  ?: ""
    )