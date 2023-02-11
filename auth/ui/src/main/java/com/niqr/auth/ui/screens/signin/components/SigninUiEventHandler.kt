package com.niqr.auth.ui.screens.signin.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.niqr.auth.ui.screens.signin.SigninAction
import com.niqr.auth.ui.screens.signin.SigninEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun SigninUiEventHandler(
    uiEvent: Flow<SigninEvent>,
    onAction: (SigninAction) -> Unit,
    snackbarHost: SnackbarHostState,
    onNavigateToForgot: () -> Unit,
    onNavigateToSignup: () -> Unit,
    onSuccess: () -> Unit
) {
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
                        if (snackbarHost.currentSnackbarData == null)
                            snackbarHost.showSnackbar(it.message)
                    }
                }
            }
        }
    }
}