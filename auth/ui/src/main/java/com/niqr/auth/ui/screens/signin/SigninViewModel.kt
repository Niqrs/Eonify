package com.niqr.auth.ui.screens.signin

import androidx.activity.result.ActivityResult
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.niqr.auth.domain.model.SignInWIthEmailResult
import com.niqr.auth.ui.handlers.GoogleAuthResultHandler
import com.niqr.auth.ui.handlers.SignInWithEmailHandler
import com.niqr.auth.ui.handlers.model.GoogleAuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SigninViewModel @Inject constructor(
    private val googleSignInClient: GoogleSignInClient,
    private val googleAuthResultHandler: GoogleAuthResultHandler,
    private val signInWithEmailHandler: SignInWithEmailHandler
): ViewModel() {

    var uiState by mutableStateOf(SigninUiState())
        private set

    private val _uiEvent = Channel<SigninEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAction(event: SigninAction) {
        when(event) {
            SigninAction.OnNavigateToForgot -> onNavigateToForgot()
            SigninAction.OnNavigateToSignup -> onNavigateToSignup()

            is SigninAction.OnEmailChange -> onEmailChange(event.email)
            is SigninAction.OnPasswordChange -> onPasswordChange(event.password)
            is SigninAction.OnPasswordVisibilityChange -> onPasswordVisibilityChange(event.visible)

            SigninAction.OnSignupWithFacebook -> onSignupWithFacebook()
            SigninAction.OnSignupWithGoogle -> onSignupWithGoogle()
            is SigninAction.OnSignupWithGoogleResult -> onSignupWithGoogleResult(event.result)

            SigninAction.OnLoginClick -> onLoginClick()
        }
    }


    private fun onNavigateToForgot() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(SigninEvent.NavigateToForgot)
        }
    }

    private fun onNavigateToSignup() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(SigninEvent.NavigateToSignup)
        }
    }

    private fun onEmailChange(email: String) {
        uiState = uiState.copy(
            email = email
        )
    }

    private fun onPasswordChange(password: String) {
        uiState = uiState.copy(
            password = password
        )
    }

    private fun onPasswordVisibilityChange(visible: Boolean) {
        uiState = uiState.copy(
            passwordVisible = visible
        )
    }

    private fun onSignupWithFacebook() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(
                SigninEvent.ShowSnackbar("Not yet implemented")
            )
        }
    }

    private fun onSignupWithGoogle() {
        val intent = googleSignInClient.signInIntent
        viewModelScope.launch {
            _uiEvent.send(SigninEvent.LaunchGoogleAuth(intent))
        }
    }

    private fun onSignupWithGoogleResult(result: ActivityResult) {
        viewModelScope.launch {
            when(googleAuthResultHandler.handle(result)) {
                GoogleAuthResult.Success -> _uiEvent.send(SigninEvent.Success)
                GoogleAuthResult.Canceled -> { /*Nothing*/ }
                GoogleAuthResult.UnknownException -> {
                    _uiEvent.send(SigninEvent.ShowSnackbar("Something went wrong"))
                }
            }
        }
    }

    private fun onLoginClick() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = signInWithEmailHandler.signIn(
                email = uiState.email,
                password = uiState.password
            )
            when(result) {
                SignInWIthEmailResult.Success -> _uiEvent.send(SigninEvent.Success)
                SignInWIthEmailResult.InvalidCredentials -> {
                    _uiEvent.send(SigninEvent.ShowSnackbar("InvalidCredentials"))
                }
                SignInWIthEmailResult.UnknownException -> {
                    _uiEvent.send(SigninEvent.ShowSnackbar("UnknownException"))
                }
            }
        }
    }

}