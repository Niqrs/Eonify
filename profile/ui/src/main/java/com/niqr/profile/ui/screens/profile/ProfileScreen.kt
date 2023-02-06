package com.niqr.profile.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.niqr.core_ui.theme.EonifyTheme
import com.niqr.profile.ui.screens.profile.components.AccountInfoItem
import com.niqr.profile.ui.screens.profile.components.LogOutButton
import com.niqr.profile.ui.screens.profile.components.ProfileDivider
import com.niqr.profile.ui.screens.profile.components.ProfileImage
import com.niqr.profile.ui.screens.profile.components.ProfileTitle
import com.niqr.profile.ui.screens.profile.components.ProfileTopAppBar
import com.niqr.profile.ui.screens.profile.components.ProfileUsername
import kotlinx.coroutines.flow.Flow

@Composable
internal fun ProfileScreen(
    uiState: ProfileUiState,
    uiEvent: Flow<ProfileEvent>,
    onAction: (ProfileAction) -> Unit,
    onSignOut: () -> Unit,
    onOpenBio: () -> Unit
) {
    LaunchedEffect(true) {
        uiEvent.collect {
            when(it) {
                ProfileEvent.SignOut -> onSignOut()
                ProfileEvent.OpenBio -> onOpenBio()
            }
        }
    }

    Box(
        modifier = Modifier
            .background(EonifyTheme.colorScheme.background)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxWidth(),
            ) {
                ProfileImage(
                    photoUrl = uiState.user.photoUrl
                )

                ProfileTopAppBar(
                    onMoreClick = {}
                )

                ProfileUsername(
                    name = uiState.user.displayName
                )
            }

            Column(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .weight(1f)
            ) {
                ProfileTitle(text = "Account")
                AccountInfoItem(
                    text = uiState.user.email,
                    hint = "Email",
                )
                ProfileDivider()
                AccountInfoItem(
                    text = uiState.user.bio,
                    hint = "Add a few words about yourself",
                    onClick = { onAction(ProfileAction.OnOpenBio) }
                )
                LogOutButton(
                    onClick = { onAction(ProfileAction.OnSignOut) }
                )
            }
        }
    }
}