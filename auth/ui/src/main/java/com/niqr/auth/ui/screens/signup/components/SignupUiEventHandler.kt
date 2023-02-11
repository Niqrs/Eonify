package com.niqr.auth.ui.screens.signup.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.niqr.auth.ui.screens.signup.SignupAction
import com.niqr.auth.ui.screens.signup.SignupEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun SignupUiEventHandler(
    uiEvent: Flow<SignupEvent>,
    onAction: (SignupAction) -> Unit,
    snackbarHost: SnackbarHostState,
    onNavigateToSignin: () -> Unit,
    onSuccess: () -> Unit
) {
    val scope = rememberCoroutineScope()

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        onAction(SignupAction.OnSignupWithGoogleResult(result))
    }

    LaunchedEffect(key1 = true) {
        uiEvent.collect {
            when (it) {
                SignupEvent.NavigateToSignin -> onNavigateToSignin()
                is SignupEvent.ShowSnackbar -> {
                    scope.launch {
                        if (snackbarHost.currentSnackbarData == null)
                            snackbarHost.showSnackbar(it.message)
                    }
                }
                is SignupEvent.LaunchGoogleAuth -> {
                    launcher.launch(it.intent)
                }
                SignupEvent.Success -> onSuccess()
            }
        }
    }
}