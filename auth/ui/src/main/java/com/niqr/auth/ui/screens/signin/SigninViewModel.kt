package com.niqr.auth.ui.screens.signin

import androidx.activity.result.ActivityResult
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.niqr.auth.ui.handlers.GoogleAuthResultHandler
import com.niqr.auth.ui.handlers.SignInWithEmailHandler
import com.niqr.core_util.filterWhitespaces
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
            email = email.filterWhitespaces().take(64)
        )
    }

    private fun onPasswordChange(password: String) {
        uiState = uiState.copy(
            password = password.filterWhitespaces().take(32)
        )
    }

    private fun onPasswordVisibilityChange(visible: Boolean) {
        uiState = uiState.copy(
            passwordVisible = visible
        )
    }

    private fun onSignupWithFacebook() {
//        uiState = uiState.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(
                SigninEvent.ShowSnackbar("Not yet implemented")
            )
//            uiState = uiState.copy(isLoading = false)
        }
    }

//    private fun onSignupWithFacebookResult() {}

    private fun onSignupWithGoogle() {
        uiState = uiState.copy(isLoading = true)
        val intent = googleSignInClient.signInIntent
        viewModelScope.launch {
            _uiEvent.send(SigninEvent.LaunchGoogleAuth(intent))
        }
    }

    private fun onSignupWithGoogleResult(result: ActivityResult) {
        viewModelScope.launch {
            val authResult = googleAuthResultHandler.handle(result)
            uiState = uiState.copy(isLoading = false)
            when(authResult) {
                is Either.Right -> _uiEvent.send(SigninEvent.Success)
                is Either.Left -> {
                    authResult.value.let {
                        if (!it.isNullOrBlank())
                            _uiEvent.send(SigninEvent.ShowSnackbar(it))
                    }
                }
            }
        }
    }

    private fun onLoginClick() {
        if (uiState.isLoading) return
        uiState = uiState.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            if (checkFields())
                login()
            uiState = uiState.copy(isLoading = false)
        }
    }

    private suspend fun checkFields(): Boolean {
        return if (uiState.email.isBlank() or uiState.password.isBlank()) {
            _uiEvent.send(SigninEvent.ShowSnackbar("Fields can't be empty"))
            false
        } else {
            true
        }
    }

    private suspend fun login() {
        val result = signInWithEmailHandler.signIn(
            email = uiState.email,
            password = uiState.password
        )
        when (result) {
            is Either.Right -> _uiEvent.send(SigninEvent.Success)
            is Either.Left -> _uiEvent.send(SigninEvent.ShowSnackbar(result.value))
        }
    }
}