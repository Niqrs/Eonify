package com.niqr.profile.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.niqr.core_ui.theme.EonifyTheme
import kotlinx.coroutines.flow.Flow

@Composable
internal fun ProfileScreen(
    uiState: ProfileUiState,
    uiEvent: Flow<ProfileEvent>,
    onAction: (ProfileAction) -> Unit,
    onSignOut: () -> Unit
) {
    LaunchedEffect(true) {
        uiEvent.collect {
            when(it) {
                ProfileEvent.SignOut -> onSignOut()
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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = uiState.user.uid, color = EonifyTheme.colorScheme.textMediumContrast)
            Text(text = uiState.user.photoUrl, color = EonifyTheme.colorScheme.textMediumContrast)
            Text(text = uiState.user.displayName, color = EonifyTheme.colorScheme.textMediumContrast)
            Text(text = uiState.user.email, color = EonifyTheme.colorScheme.textMediumContrast)
            Button(
                onClick = {
                    onAction(ProfileAction.OnSignOut)
                }
            ) {
                Text(text = "SignOut")
            }
        }
    }
}