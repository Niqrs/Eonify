package com.niqr.auth.ui.screens.forgot

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niqr.auth.ui.screens.forgot.components.ForgotContinueButton
import com.niqr.auth.ui.screens.forgot.components.ForgotDescription
import com.niqr.auth.ui.screens.forgot.components.ForgotEmailTextField
import com.niqr.auth.ui.screens.forgot.components.ForgotHeadIcon
import com.niqr.auth.ui.screens.forgot.components.ForgotHeader
import com.niqr.auth.ui.screens.forgot.components.ForgotNavigateUpButton
import com.niqr.auth.ui.screens.forgot.components.ForgotUiEventHandler
import com.niqr.core_ui.theme.EonifyTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
internal fun ForgotScreen(
    uiState: ForgotUiState,
    uiEvent: Flow<ForgotEvent>,
    onAction: (ForgotAction) -> Unit,
    onNavigateUp: () -> Unit
) {
    val snackbar = remember { SnackbarHostState() }

    ForgotUiEventHandler(
        uiEvent = uiEvent,
        snackbarHost = snackbar,
        onNavigateUp = onNavigateUp
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(snackbar)
        },
        containerColor = EonifyTheme.colorScheme.background,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ForgotNavigateUpButton(
                onClick = { onAction(ForgotAction.OnNavigateUp) }
            )
            Spacer(modifier = Modifier.height(32.dp))

            ForgotHeadIcon()
            Spacer(modifier = Modifier.height(22.dp))

            ForgotHeader()
            Spacer(modifier = Modifier.height(16.dp))

            ForgotDescription()
            Spacer(modifier = Modifier.height(32.dp))

            ForgotEmailTextField(
                value = uiState.email,
                onValueChange = { onAction(ForgotAction.OnEmailChange(it)) },
                enabled = !uiState.isLoading and !uiState.isSuccess
            )
            Spacer(modifier = Modifier.height(32.dp))

            ForgotContinueButton(
                isSuccess = uiState.isSuccess,
                isLoading = uiState.isLoading,
                onClick = { onAction(ForgotAction.OnContinueClick) }
            )
            Spacer(modifier = Modifier.height(36.dp))
        }
    }
}

@Preview(device = "id:pixel_6_pro")
@Composable
private fun ForgotScreenPreview() {
    Surface(
        color = EonifyTheme.colorScheme.background
    ) {
        ForgotScreen(
            uiState = ForgotUiState(),
            uiEvent = emptyFlow(),
            onAction = {},
            onNavigateUp = {}
        )
    }
}