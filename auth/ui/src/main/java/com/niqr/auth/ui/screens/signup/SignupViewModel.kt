package com.niqr.auth.ui.screens.signup

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
class SignupViewModel @Inject constructor(

): ViewModel() {

    var uiState by mutableStateOf(SignupUiState())
        private set

    private val _uiEvent = Channel<SignupEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAction(event: SignupAction) {
        when(event) {
            SignupAction.OnNavigateToSignin -> onNavigateToSignin()
            is SignupAction.OnNameChange -> onNameChange(event.name)
            is SignupAction.OnEmailChange -> onEmailChange(event.email)
            is SignupAction.OnPasswordChange -> onPasswordChange(event.password)
            is SignupAction.OnPasswordVisibilityChange -> onPasswordVisibilityChange(event.visible)
            is SignupAction.OnAgreeWIthPolicyChange -> onAgreeWIthPolicyChange(event.checked)
            SignupAction.OnTermsAndPolicyClick -> onTermsAndPolicyClick()
            SignupAction.OnSignupWithFacebook -> onSignupWithFacebook()
            SignupAction.OnSignupWithGoogle -> onSignupWithGoogle()
            SignupAction.OnCreateAccountClick -> onCreateAccountClick()
        }
    }

    private fun onNavigateToSignin() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(SignupEvent.NavigateToSignin)
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
                SignupEvent.ShowSnackbar("Not yet implemented")
            )
        }
    }

    private fun onSignupWithFacebook() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(
                SignupEvent.ShowSnackbar("Not yet implemented")
            )
        }
    }

    private fun onSignupWithGoogle() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(
//                SignupEvent.ShowSnackbar("Not yet implemented")
                SignupEvent.Success
            )
        }
    }

    private fun onCreateAccountClick() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(
                SignupEvent.ShowSnackbar("Not yet implemented")
            )
        }
    }
}
