package com.niqr.profile.ui.screens.profile

import com.niqr.profile.domain.User

data class ProfileUiState(
    val user: User = User("", "", "", "")
)