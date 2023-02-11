package com.niqr.profile.ui.screens.bio

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.niqr.core_ui.theme.EonifyTheme
import com.niqr.profile.ui.screens.bio.components.BioTextField
import com.niqr.profile.ui.screens.bio.components.BioTopAppBar
import kotlinx.coroutines.flow.Flow

@Composable
fun BioScreen(
    uiState: BioUiState,
    uiEvent: Flow<BioEvent>,
    onAction: (BioAction) -> Unit,
    onNavigateUp: () -> Unit,
    onApply: () -> Unit
) {
    LaunchedEffect(true) {
        uiEvent.collect {
            when(it) {
                BioEvent.NavigateUp -> onNavigateUp()
                BioEvent.OnApply -> onApply()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BioTopAppBar(
            onNavigateUp = { onAction(BioAction.OnNavigateUp) },
            onApply = { onAction(BioAction.OnApply) }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            BioTextField(
                value = uiState.bio,
                onValueChange = { onAction(BioAction.OnBioChange(it)) },
                charactersLeft = uiState.charactersLeft
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "You can add a few lines about yourself.",
                style = EonifyTheme.typography.bodyMedium,
                color = EonifyTheme.colorScheme.textHint
            )
        }
    }
}