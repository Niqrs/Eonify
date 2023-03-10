package com.niqr.auth.ui.screens.signup

import androidx.activity.result.ActivityResult
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.niqr.auth.ui.handlers.GoogleAuthResultHandler
import com.niqr.auth.ui.handlers.SignUpWithEmailHandler
import com.niqr.core_util.filterWhitespaces
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

            is SignupAction.OnFirstNameChange -> onFirstNameChange(event.name)
            is SignupAction.OnOptionalNameChange -> onOptionalNameChange(event.name)
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

    private fun onFirstNameChange(name: String) {
        uiState = uiState.copy(
            firstName = name.filterWhitespaces().take(32)
        )
    }

    private fun onOptionalNameChange(name: String) {
        uiState = uiState.copy(
            optionalName = name.filterWhitespaces().take(32)
        )
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
            val authResult = googleAuthResultHandler.handle(result)
            uiState = uiState.copy(isLoading = false)
            when(authResult) {
                is Either.Right -> _uiEvent.send(SignupEvent.Success)
                is Either.Left -> {
                    authResult.value.let {
                        if (!it.isNullOrBlank())
                            _uiEvent.send(SignupEvent.ShowSnackbar(it))
                    }
                }
            }
        }
    }

    private fun onCreateAccountClick() {
        if (uiState.isLoading) return
        uiState = uiState.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            if (checkFields())
                createAccount()
            uiState = uiState.copy(isLoading = false)
        }
    }

    private suspend fun checkFields(): Boolean {
        return if (uiState.firstName.isBlank() or uiState.email.isBlank() or uiState.password.isBlank()) {
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
            name = userName(),
            email = uiState.email,
            password = uiState.password
        )
        when(result) {
            is Either.Right -> _uiEvent.send(SignupEvent.Success)
            is Either.Left -> _uiEvent.send(SignupEvent.ShowSnackbar(result.value))
        }
    }

    private fun userName(): String {
        return if (uiState.optionalName.isBlank()) {
            uiState.firstName
        } else {
            "${uiState.firstName} ${uiState.optionalName}"
        }
    }
}
