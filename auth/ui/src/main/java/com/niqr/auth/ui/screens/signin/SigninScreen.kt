package com.niqr.auth.ui.screens.signin

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niqr.auth.ui.R
import com.niqr.auth.ui.components.AuthButton
import com.niqr.auth.ui.components.AuthTextField
import com.niqr.auth.ui.components.AuthWithButton
import com.niqr.auth.ui.components.HeaderIconContainer
import com.niqr.core_ui.theme.EonifyTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

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
    val scope = rememberCoroutineScope()

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        onAction(SigninAction.OnSignupWithGoogleResult(result))
    }

    LaunchedEffect(key1 = true) {
        uiEvent.collect {
            when(it) {
                SigninEvent.NavigateToForgot -> onNavigateToForgot()
                SigninEvent.NavigateToSignup -> onNavigateToSignup()
                is SigninEvent.LaunchGoogleAuth -> launcher.launch(it.intent)
                SigninEvent.Success -> onSuccess()
                is SigninEvent.ShowSnackbar -> {
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
            Spacer(modifier = Modifier.height(32.dp))
            HeaderIconContainer( // Icon
                modifier = Modifier.padding(2.dp)
            ) {
                Image(
                    modifier = Modifier.size(72.dp),
                    painter = painterResource(id = R.drawable.wave),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(22.dp))

            Text( // Header
                text = "Sign In",
                style = EonifyTheme.typography.headlineMedium,
                color = EonifyTheme.colorScheme.textPrimaryHeader
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text( // Description
                text = "It was popularised in the 1960s with" +
                        " the release of Letraset sheetscontaining Lorem Ipsum.",
                style = EonifyTheme.typography.bodyMedium,
                color = EonifyTheme.colorScheme.textBody
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row( // AuthWith Buttons
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                AuthWithButton(
                    onClick = { onAction(SigninAction.OnSignupWithFacebook) },
                    text = "Facebook",
                    modifier = Modifier.weight(1f),
                    icon = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_facebook_logo_24dp),
                            contentDescription = null,
                        )
                    },
                    horizontalAlignment = Alignment.Start
                )
                AuthWithButton(
                    onClick = { onAction(SigninAction.OnSignupWithGoogle) },
                    text = "Google",
                    modifier = Modifier.weight(1f),
                    icon = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_google_logo_24dp),
                            contentDescription = null,
                        )
                    },
                    horizontalAlignment = Alignment.Start
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row( // Or
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Divider(modifier = Modifier.weight(1f), color = EonifyTheme.colorScheme.divider)
                Text(
                    text = "Or",
                    color = EonifyTheme.colorScheme.textContrast,
                    style = EonifyTheme.typography.bodyMedium
                )
                Divider(modifier = Modifier.weight(1f), color = EonifyTheme.colorScheme.divider)
            }
            Spacer(modifier = Modifier.height(16.dp))

            AuthTextField( // Email TextField
                value = uiState.email,
                onValueChange = { onAction(SigninAction.OnEmailChange(it)) },
                modifier = Modifier.fillMaxWidth(),
                hint = "Email"
            )
            Spacer(modifier = Modifier.height(16.dp))
            AuthTextField( // Password TextField
                value = uiState.password,
                onValueChange = { onAction(SigninAction.OnPasswordChange(it)) },
                modifier = Modifier.fillMaxWidth(),
                hint = "Password",
                visualTransformation = if (uiState.passwordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),
                icon = {
                    IconButton(
                        onClick = {
                            onAction(SigninAction.OnPasswordVisibilityChange(!uiState.passwordVisible))
                        }
                    ) {
                        AnimatedContent(
                            targetState = uiState.passwordVisible,
                            transitionSpec = {
                                fadeIn(animationSpec = tween(220, delayMillis = 30)) +
                                        scaleIn(
                                            initialScale = 0.92f,
                                            animationSpec = tween(220, delayMillis = 30)
                                        ) with
                                        fadeOut(animationSpec = tween(220))
                            }
                        ) {
                            Icon(
                                painter = painterResource(
                                    id = if (it) R.drawable.outline_visibility_24
                                    else R.drawable.outline_visibility_off_24
                                ),
                                contentDescription = null
                            )
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(8.dp))

            ClickableText( // Navigate to Forgot
                text = AnnotatedString("Forgot Password?"),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                style = EonifyTheme.typography.bodyMedium.copy(
                    color = EonifyTheme.colorScheme.textHint,
                    textAlign = TextAlign.End
                ),
                onClick = { onAction(SigninAction.OnNavigateToForgot) }
            )
            Spacer(modifier = Modifier.height(28.dp))

            AuthButton( // AuthButton
                onClick = {onAction(SigninAction.OnLoginClick) },
                text = "Log In",
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(14.dp))

            ClickableText( // Navigate to Sign-Up
                text = buildAnnotatedString {
                    append("Don’t have account? ")
                    withStyle(
                        style = EonifyTheme.typography.bodyMedium.copy(
                            color = EonifyTheme.colorScheme.textAction,
                            textAlign = TextAlign.Start
                        ).toSpanStyle(),
                    ) {
                        append("Sign Up")
                    }
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                style = EonifyTheme.typography.bodyMedium.copy(
                    color = EonifyTheme.colorScheme.textMediumContrast,
                    textAlign = TextAlign.Start
                ),
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