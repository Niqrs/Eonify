package com.niqr.profile.ui.screens.bio

import androidx.compose.runtime.Composable
import com.niqr.profile.ui.components.ProfileTextField
import kotlinx.coroutines.flow.Flow

@Composable
fun BioScreen(
    uiState: BioUiState,
    uiEvent: Flow<BioEvent>,
    onAction: (BioAction) -> Unit,
    onNavigateUp: () -> Unit,
    onApply: () -> Unit
) {
    ProfileTextField(
        value = uiState.bio,
        onValueChange = { onAction(BioAction.OnBioChange(it)) }
    )
}