package com.niqr.auth.ui.screens.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignupViewModel @Inject constructor(

): ViewModel() {

    var uiState by mutableStateOf(SignupUiState())
        private set

    private val _uiEvent = Channel<SignupUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SignupEvent) {
        when(event) {
            SignupEvent.OnNavigateToSignin -> onNavigateToSignin()
            is SignupEvent.OnNameChange -> onNameChange(event.name)
            is SignupEvent.OnEmailChange -> onEmailChange(event.email)
            is SignupEvent.OnPasswordChange -> onPasswordChange(event.password)
            is SignupEvent.OnPasswordVisibilityChange -> onPasswordVisibilityChange(event.visible)
            is SignupEvent.OnAgreeWIthPolicyChange -> onAgreeWIthPolicyChange(event.checked)
            SignupEvent.OnTermsAndPolicyClick -> onTermsAndPolicyClick()
            SignupEvent.OnSignupWithFacebook -> onSignupWithFacebook()
            SignupEvent.OnSignupWithGoogle -> onSignupWithGoogle()
            SignupEvent.OnCreateAccountClick -> onCreateAccountClick()
        }
    }

    private fun onNavigateToSignin() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(SignupUiEvent.NavigateToSignin)
        }
    }

    private fun onNameChange(name: String) {
        uiState = uiState.copy(
            name = name
        )
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

    private fun onAgreeWIthPolicyChange(checked: Boolean) {
        uiState = uiState.copy(
            agreedWithPolicy = checked
        )
    }

    private fun onTermsAndPolicyClick() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(
                SignupUiEvent.ShowSnackbar("Not yet implemented")
            )
        }
    }

    private fun onSignupWithFacebook() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(
                SignupUiEvent.ShowSnackbar("Not yet implemented")
            )
        }
    }

    private fun onSignupWithGoogle() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(
                SignupUiEvent.ShowSnackbar("Not yet implemented")
            )
        }
    }

    private fun onCreateAccountClick() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(
                SignupUiEvent.ShowSnackbar("Not yet implemented")
            )
        }
    }
}
