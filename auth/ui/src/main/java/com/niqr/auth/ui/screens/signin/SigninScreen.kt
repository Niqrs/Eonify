package com.niqr.auth.ui.screens.signin

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.niqr.auth.ui.screens.signin.components.SiginFacebookButton
import com.niqr.auth.ui.screens.signin.components.SigninAuthButton
import com.niqr.auth.ui.screens.signin.components.SigninDescription
import com.niqr.auth.ui.screens.signin.components.SigninDivider
import com.niqr.auth.ui.screens.signin.components.SigninDoNotHaveAccount
import com.niqr.auth.ui.screens.signin.components.SigninEmailTextField
import com.niqr.auth.ui.screens.signin.components.SigninForgotPassword
import com.niqr.auth.ui.screens.signin.components.SigninGoogleButton
import com.niqr.auth.ui.screens.signin.components.SigninHeadIcon
import com.niqr.auth.ui.screens.signin.components.SigninHeader
import com.niqr.auth.ui.screens.signin.components.SigninPasswordTextField
import com.niqr.auth.ui.screens.signin.components.SigninUiEventHandler
import com.niqr.core_ui.theme.EonifyTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
internal fun SigninScreen(
    uiState: SigninUiState,
    uiEvent: Flow<SigninEvent>,
    onAction: (SigninAction) -> Unit,
    onNavigateToForgot: () -> Unit,
    onNavigateToSignup: () -> Unit,
    onSuccess: () -> Unit
) {
    val snackbar = remember { SnackbarHostState() }

    SigninUiEventHandler(
        uiEvent = uiEvent,
        onAction = onAction,
        snackbarHost = snackbar,
        onNavigateToForgot = onNavigateToForgot,
        onNavigateToSignup = onNavigateToSignup,
        onSuccess = onSuccess
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
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
            Spacer(modifier = Modifier.height(32.dp))

            SigninHeadIcon()
            Spacer(modifier = Modifier.height(22.dp))

            SigninHeader()
            Spacer(modifier = Modifier.height(16.dp))

            SigninDescription()
            Spacer(modifier = Modifier.height(16.dp))

            Row( // AuthWith Buttons
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                SiginFacebookButton(
                    onClick = { onAction(SigninAction.OnSignupWithFacebook) },
                    isLoading = uiState.isLoading
                )

                SigninGoogleButton(
                    onClick = { onAction(SigninAction.OnSignupWithGoogle) },
                    isLoading = uiState.isLoading
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            SigninDivider()
            Spacer(modifier = Modifier.height(16.dp))

            SigninEmailTextField(
                value = uiState.email,
                onValueChange = { onAction(SigninAction.OnEmailChange(it)) },
                loading = uiState.isLoading
            )
            Spacer(modifier = Modifier.height(16.dp))

            SigninPasswordTextField(
                value = uiState.password,
                onValueChange = { onAction(SigninAction.OnPasswordChange(it)) },
                passwordVisible = uiState.passwordVisible,
                onPasswordVisibilityChange = { onAction(SigninAction.OnPasswordVisibilityChange(!uiState.passwordVisible)) },
                loading = uiState.isLoading
            )
            Spacer(modifier = Modifier.height(8.dp))

            SigninForgotPassword(
                loading = uiState.isLoading,
                onClick = { onAction(SigninAction.OnNavigateToForgot) }
            )
            Spacer(modifier = Modifier.height(28.dp))

            SigninAuthButton(
                loading = uiState.isLoading,
                onClick = { onAction(SigninAction.OnLoginClick) }
            )
            Spacer(modifier = Modifier.height(14.dp))

            SigninDoNotHaveAccount(
                loading = uiState.isLoading,
                onClick = { onAction(SigninAction.OnNavigateToSignup) }
            )
            Spacer(modifier = Modifier.height(36.dp))
        }
    }
}

@Preview(device = "id:pixel_6_pro")
@Composable
private fun SigninScreenPreview() {
    Surface(
        color = EonifyTheme.colorScheme.background
    ) {
        SigninScreen(
            uiState = SigninUiState(),
            uiEvent = emptyFlow(),
            onAction = {},
            onNavigateToForgot = {},
            onNavigateToSignup = {},
            onSuccess = {}
        )
    }
}