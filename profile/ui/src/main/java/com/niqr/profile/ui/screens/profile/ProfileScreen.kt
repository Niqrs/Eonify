package com.niqr.profile.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.niqr.core_ui.theme.EonifyTheme
import com.niqr.profile.ui.screens.profile.components.AccountInfoItem
import com.niqr.profile.ui.screens.profile.components.LogOutButton
import com.niqr.profile.ui.screens.profile.components.ProfileChangeImageButton
import com.niqr.profile.ui.screens.profile.components.ProfileDivider
import com.niqr.profile.ui.screens.profile.components.ProfileDropdownMenu
import com.niqr.profile.ui.screens.profile.components.ProfileImage
import com.niqr.profile.ui.screens.profile.components.ProfileSystemUiController
import com.niqr.profile.ui.screens.profile.components.ProfileTitle
import com.niqr.profile.ui.screens.profile.components.ProfileTopAppBar
import com.niqr.profile.ui.screens.profile.components.ProfileUiEventHandler
import com.niqr.profile.ui.screens.profile.components.ProfileUsername
import kotlinx.coroutines.flow.Flow

@Composable
internal fun ProfileScreen(
    uiState: ProfileUiState,
    uiEvent: Flow<ProfileEvent>,
    onAction: (ProfileAction) -> Unit,
    onSignOut: () -> Unit,
    onOpenBio: () -> Unit,
    onOpenEditName: () -> Unit
) {
    ProfileUiEventHandler(
        uiEvent = uiEvent,
        onAction = onAction,
        onSignOut = onSignOut,
        onOpenBio = onOpenBio,
        onOpenEditName = onOpenEditName
    )
    ProfileSystemUiController()

    Box(
        modifier = Modifier
            .background(EonifyTheme.colorScheme.background)
            .navigationBarsPadding()
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
                    onExpandClick = { onAction(ProfileAction.OnExpandMenu) }
                )

                ProfileDropdownMenu(
                    expanded = uiState.isMenuExpanded,
                    onDismissRequest = { onAction(ProfileAction.OnMenuDismiss) },
                    onEditNameClick = { onAction(ProfileAction.OnEditName) },
                    onSetPhotoClick = { onAction(ProfileAction.OnPickImage) },
                    onLogOutClick = { onAction(ProfileAction.OnSignOut) }
                )

                ProfileUsername(
                    name = uiState.user.displayName
                )

                ProfileChangeImageButton(
                    onClick = { onAction(ProfileAction.OnPickImage) }
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