package com.niqr.auth.ui.screens.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SigninViewModel @Inject constructor(

): ViewModel() {

    var uiState by mutableStateOf(SigninUiState())
        private set

    private val _uiEvent = Channel<SigninUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SigninEvent) {
        when(event) {
            SigninEvent.OnNavigateToForgot -> onNavigateToForgot()
            SigninEvent.OnNavigateToSignup -> onNavigateToSignup()
            is SigninEvent.OnEmailChange -> onEmailChange(event.email)
            is SigninEvent.OnPasswordChange -> onPasswordChange(event.password)
            is SigninEvent.OnPasswordVisibilityChange -> onPasswordVisibilityChange(event.visible)
            SigninEvent.OnSignupWithFacebook -> onSignupWithFacebook()
            SigninEvent.OnSignupWithGoogle -> onSignupWithGoogle()
            SigninEvent.OnLoginClick -> onLoginClick()
        }
    }

    private fun onNavigateToForgot() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(SigninUiEvent.NavigateToForgot)
        }
    }

    private fun onNavigateToSignup() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(SigninUiEvent.NavigateToSignup)
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
                SigninUiEvent.ShowSnackbar("Not yet implemented")
            )
        }
    }

    private fun onSignupWithGoogle() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(
                SigninUiEvent.ShowSnackbar("Not yet implemented")
            )
        }
    }

    private fun onLoginClick() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(
                SigninUiEvent.ShowSnackbar("Not yet implemented")
            )
        }
    }

}