package com.niqr.profile.ui.screens.bio.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.niqr.profile.ui.screens.bio.BioEvent
import kotlinx.coroutines.flow.Flow

@Composable
fun BioUiEventHandler(
    uiEvent: Flow<BioEvent>,
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
}