package com.niqr.auth.ui.screens.signup

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.niqr.auth.ui.components.AuthCheckbox
import com.niqr.auth.ui.screens.signup.components.SignupAuthButton
import com.niqr.auth.ui.screens.signup.components.SignupDescription
import com.niqr.auth.ui.screens.signup.components.SignupDivider
import com.niqr.auth.ui.screens.signup.components.SignupEmailTextField
import com.niqr.auth.ui.screens.signup.components.SignupFacebookButton
import com.niqr.auth.ui.screens.signup.components.SignupFirstNameTextField
import com.niqr.auth.ui.screens.signup.components.SignupGoogleButton
import com.niqr.auth.ui.screens.signup.components.SignupHaveAccount
import com.niqr.auth.ui.screens.signup.components.SignupHeadIcon
import com.niqr.auth.ui.screens.signup.components.SignupHeader
import com.niqr.auth.ui.screens.signup.components.SignupOptionalNameTextField
import com.niqr.auth.ui.screens.signup.components.SignupPasswordTextField
import com.niqr.auth.ui.screens.signup.components.SignupPolicy
import com.niqr.auth.ui.screens.signup.components.SignupUiEventHandler
import com.niqr.core_ui.theme.EonifyTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    uiState: SignupUiState,
    uiEvent: Flow<SignupEvent>,
    onAction: (SignupAction) -> Unit,
    onNavigateToSignin: () -> Unit,
    onSuccess: () -> Unit
) {
    val snackbar = remember { SnackbarHostState() }

    SignupUiEventHandler(
        uiEvent = uiEvent,
        onAction = onAction,
        snackbarHost = snackbar,
        onNavigateToSignin = onNavigateToSignin,
        onSuccess = onSuccess
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
            Spacer(modifier = Modifier.height(32.dp))

            SignupHeadIcon()
            Spacer(modifier = Modifier.height(22.dp))

            SignupHeader()
            Spacer(modifier = Modifier.height(16.dp))

            SignupDescription()
            Spacer(modifier = Modifier.height(16.dp))

            Row( // AuthWith Buttons
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                SignupFacebookButton(
                    onClick = { onAction(SignupAction.OnSignupWithFacebook) },
                    isLoading = uiState.isLoading
                )

                SignupGoogleButton(
                    onClick = { onAction(SignupAction.OnSignupWithGoogle) },
                    isLoading = uiState.isLoading
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            SignupDivider()
            Spacer(modifier = Modifier.height(16.dp))

            Row {
                SignupFirstNameTextField(
                    value = uiState.firstName,
                    onValueChange = { onAction(SignupAction.OnFirstNameChange(it)) },
                    loading = uiState.isLoading
                )
                Spacer(modifier = Modifier.width(12.dp))

                SignupOptionalNameTextField(
                    value = uiState.optionalName,
                    onValueChange = { onAction(SignupAction.OnOptionalNameChange(it)) },
                    loading = uiState.isLoading
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            SignupEmailTextField(
                value = uiState.email,
                onValueChange = { onAction(SignupAction.OnEmailChange(it)) },
                loading = uiState.isLoading
            )
            Spacer(modifier = Modifier.height(16.dp))

            SignupPasswordTextField(
                value = uiState.password,
                onValueChange = { onAction(SignupAction.OnPasswordChange(it)) },
                passwordVisible = uiState.passwordVisible,
                onPasswordVisibilityChange = { onAction(SignupAction.OnPasswordVisibilityChange(it)) },
                loading = uiState.isLoading
            )
            Spacer(modifier = Modifier.height(14.dp))

            Row( // Terms of Service and Privacy Policy
                modifier = Modifier.fillMaxWidth()
            ) {
                AuthCheckbox(
                    checked = uiState.agreedWithPolicy,
                    onCheckedChange = { onAction(SignupAction.OnAgreeWIthPolicyChange(it)) },
                    enabled = !uiState.isLoading,
                )
                Spacer(modifier = Modifier.width(12.dp))

                SignupPolicy(
                    loading = uiState.isLoading,
                    onClick = {
                        onAction(SignupAction.OnTermsAndPolicyClick)
                    }
                )
            }
            Spacer(modifier = Modifier.height(28.dp))

            SignupAuthButton(
                loading = uiState.isLoading,
                onClick = { onAction(SignupAction.OnCreateAccountClick) }
            )
            Spacer(modifier = Modifier.height(14.dp))

            SignupHaveAccount(
                loading = uiState.isLoading,
                onClick = {
                    onAction(SignupAction.OnNavigateToSignin)
                }
            )
            Spacer(modifier = Modifier.height(36.dp))
        }
    }
}

@Preview(device = "id:pixel_6_pro")
@Composable
private fun SignupScreenPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = EonifyTheme.colorScheme.background
    ) {
        SignupScreen(
            uiState = SignupUiState(),
            uiEvent = emptyFlow(),
            onAction = {},
            onNavigateToSignin = {},
            onSuccess = {}
        )
    }
}