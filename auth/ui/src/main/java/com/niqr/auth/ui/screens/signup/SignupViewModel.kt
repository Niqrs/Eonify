package com.niqr.auth.ui.screens.signup

import androidx.activity.result.ActivityResult
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.niqr.auth.domain.model.SignUpWithEmailResult
import com.niqr.auth.ui.handlers.GoogleAuthResultHandler
import com.niqr.auth.ui.handlers.SignUpWithEmailHandler
import com.niqr.auth.ui.handlers.model.GoogleAuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val googleSignInClient: GoogleSignInClient,
    private val googleAuthResultHandler: GoogleAuthResultHandler,
    private val signUpWithEmailHandler: SignUpWithEmailHandler
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
            is SignupAction.OnSignupWithGoogleResult -> onSignupWithGoogleResult(event.result)
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
//        uiState = uiState.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(
                SignupEvent.ShowSnackbar("Not yet implemented")
            )
//            uiState = uiState.copy(isLoading = false)
        }
    }

//    private fun onSignupWithFacebookResult() {}

    private fun onSignupWithGoogle() {
        uiState = uiState.copy(isLoading = true)
        val intent = googleSignInClient.signInIntent
        viewModelScope.launch {
            _uiEvent.send(SignupEvent.LaunchGoogleAuth(intent))
        }
    }

    private fun onSignupWithGoogleResult(result: ActivityResult) {
        viewModelScope.launch {
            when(googleAuthResultHandler.handle(result)) {
                GoogleAuthResult.Success -> {
                    uiState = uiState.copy(isLoading = false)
                    _uiEvent.send(SignupEvent.Success)
                }
                GoogleAuthResult.Canceled -> {
                    uiState = uiState.copy(isLoading = false)
                }
                GoogleAuthResult.UnknownException -> {
                    uiState = uiState.copy(isLoading = false)
                    _uiEvent.send(SignupEvent.ShowSnackbar("Something went wrong"))
                }
            }
        }
    }

    private fun onCreateAccountClick() {
        if (uiState.isLoading) return
        uiState = uiState.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            if (checkFields()) {
                createAccount()
            }
            uiState = uiState.copy(isLoading = false)
        }
    }

    private suspend fun checkFields(): Boolean {
        return if (uiState.name.isBlank() or uiState.email.isBlank() or uiState.password.isBlank()) {
            _uiEvent.send(SignupEvent.ShowSnackbar("Fields can't be empty"))
            false
        } else if (!uiState.agreedWithPolicy) {
            _uiEvent.send(SignupEvent.ShowSnackbar("You should agree to the policy"))
            false
        } else {
            true
        }
    }

    private suspend fun createAccount() {
        val result = signUpWithEmailHandler.signUp(
            email = uiState.email,
            password = uiState.password
        )
        when(result) {
            SignUpWithEmailResult.Success -> _uiEvent.send(SignupEvent.Success)
            SignUpWithEmailResult.WeakPassword -> { //TODO: All the error messages should be as value of a single Error object parameter
                _uiEvent.send(SignupEvent.ShowSnackbar("Weak password"))
            }
            SignUpWithEmailResult.InvalidCredentials -> {
                _uiEvent.send(SignupEvent.ShowSnackbar("Invalid email"))
            }
            SignUpWithEmailResult.UserCollision -> {
                _uiEvent.send(SignupEvent.ShowSnackbar("User with this email already exist"))
            }
            SignUpWithEmailResult.UnknownException -> {
                _uiEvent.send(SignupEvent.ShowSnackbar("Something went wrong"))
            }
        }
    }
}
