package com.niqr.auth.ui.screens.forgot

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niqr.auth.ui.R
import com.niqr.auth.ui.components.AuthButton
import com.niqr.auth.ui.components.AuthTextField
import com.niqr.auth.ui.components.HeaderIconContainer
import com.niqr.core_ui.theme.EonifyTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ForgotScreen(
    uiState: ForgotUiState,
    uiEvent: Flow<ForgotUiEvent>,
    onEvent: (ForgotEvent) -> Unit,
    onNavigateUp: () -> Unit
) {
    val snackbar = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        uiEvent.collect {
            when(it) {
                ForgotUiEvent.NavigateUp -> onNavigateUp()
                is ForgotUiEvent.ShowSnackbar -> {
                    scope.launch {
                        if (snackbar.currentSnackbarData == null)
                            snackbar.showSnackbar(it.message)
                    }
                }
            }
        }
    }

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
            IconButton( // Navigate Up Button
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .align(Start),
                onClick = { onEvent(ForgotEvent.OnNavigateUp) }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = EonifyTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.height(32.dp))

            HeaderIconContainer( // Icon
                modifier = Modifier.padding(2.dp)
            ) {
                Image(
                    modifier = Modifier.size(72.dp),
                    painter = painterResource(id = R.drawable.letter),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(22.dp))

            Text( // Header
                text = "Forgot Password",
                style = EonifyTheme.typography.headlineMedium,
                color = EonifyTheme.colorScheme.textPrimaryHeader,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text( // Description
                text = "It was popularised in the 1960s with" +
                        " the release of Letraset sheetscontaining Lorem Ipsum.",
                style = EonifyTheme.typography.bodyMedium,
                color = EonifyTheme.colorScheme.textBody
            )
            Spacer(modifier = Modifier.height(32.dp))

            AuthTextField( // Email TextField
                value = uiState.email,
                onValueChange = { onEvent(ForgotEvent.OnEmailChange(it)) },
                modifier = Modifier.fillMaxWidth(),
                hint = "Email"
            )
            Spacer(modifier = Modifier.height(32.dp))

            AuthButton( // AuthButton
                onClick = {onEvent(ForgotEvent.OnContinueClick) },
                text = "Continue",
                modifier = Modifier
                    .fillMaxWidth()
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
            onEvent = {},
            onNavigateUp = {}
        )
    }
}