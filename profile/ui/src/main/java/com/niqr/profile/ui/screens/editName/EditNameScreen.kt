package com.niqr.profile.ui.screens.editName

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.niqr.profile.ui.screens.editName.components.EditNameTopAppBar
import com.niqr.profile.ui.screens.editName.components.EditNameUiEventHandler
import com.niqr.profile.ui.screens.editName.components.FirstNameTextField
import com.niqr.profile.ui.screens.editName.components.OptionalNameTextField
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNameScreen(
    uiState: EditNameUiState,
    uiEvent: Flow<EditNameEvent>,
    onAction: (EditNameAction) -> Unit,
    onNavigateUp: () -> Unit,
    onApply: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    EditNameUiEventHandler(
        uiEvent = uiEvent,
        snackbarHost = snackbarHostState,
        onNavigateUp = onNavigateUp,
        onApply = onApply
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            EditNameTopAppBar(
                onNavigateUp = { onAction(EditNameAction.OnNavigateUp) },
                onApply = { onAction(EditNameAction.OnApply) }
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = 16.dp)
                .padding(horizontal = 16.dp)
        ) {
            FirstNameTextField(
                value = uiState.firstName,
                onValueChange = { onAction(EditNameAction.OnFirstNameChange(it)) }
            )

            OptionalNameTextField(
                value = uiState.optionalName,
                onValueChange = { onAction(EditNameAction.OnOptionalNameChange(it)) }
            )
        }
    }
}