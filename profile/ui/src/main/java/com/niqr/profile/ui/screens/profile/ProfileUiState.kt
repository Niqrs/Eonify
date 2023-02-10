package com.niqr.profile.ui.screens.profile

import com.niqr.profile.domain.User

data class ProfileUiState(
    val user: UserUiState = UserUiState(),
    val isMenuExpanded: Boolean = false
)

data class UserUiState(
    val photoUrl: String = "",
    val displayName: String = "",
    val email: String = "",
    val bio: String = ""
)

fun User.toUiState(): UserUiState {
    return UserUiState(
        photoUrl = this.photoUrl,
        displayName = this.displayName,
        email = this.email,
        bio = this.bio.ifBlank { "Bio" }
    )
}