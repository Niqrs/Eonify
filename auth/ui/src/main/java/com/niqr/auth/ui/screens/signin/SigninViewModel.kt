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
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(
                SigninEvent.ShowSnackbar("Not yet implemented")
            )
        }
    }

    private fun onLoginClick() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(
                SigninEvent.ShowSnackbar("Not yet implemented")
            )
        }
    }

}