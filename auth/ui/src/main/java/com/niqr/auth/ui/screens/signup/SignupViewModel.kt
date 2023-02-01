package com.niqr.auth.ui.screens.signup

import android.app.Activity
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.niqr.auth.domain.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val repo: AuthRepository,
    private val oneTapClient: SignInClient
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
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(
                SignupEvent.ShowSnackbar("Not yet implemented")
            )
        }
    }

    private fun onSignupWithGoogle() {
        viewModelScope.launch(Dispatchers.IO) {
            val oneTapSignInResponse = repo.oneTapSignInWithGoogle()
            if (oneTapSignInResponse == null) { //TODO: FIX THIS

            } else {
                val intent = IntentSenderRequest.Builder(oneTapSignInResponse.pendingIntent.intentSender).build()
                _uiEvent.send(
                    SignupEvent.LaunchGoogleAuth(intent)
                )
            }
        }
    }

    private fun onSignupWithGoogleResult(result: ActivityResult) {
        viewModelScope.launch {
            Log.d("TAG", "111111")
            if (result.resultCode == Activity.RESULT_OK) {
                try {
                    Log.d("TAG", "222222")
                    val credentials = oneTapClient.getSignInCredentialFromIntent(result.data)
                    val googleIdToken = credentials.googleIdToken
                    val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
                    Log.d("TAG", "33333")
                    val signInWithGoogleResponse = repo.firebaseSignInWithGoogle(googleCredentials)
                    Log.d("TAG", "44444")
                    if (signInWithGoogleResponse) {
                        Log.d("TAG", "5555555")
                        _uiEvent.send(SignupEvent.Success)
                    } else {
                        _uiEvent.send(SignupEvent.ShowSnackbar("Error"))
                    }
                } catch (it: ApiException) {
                    Log.d("TAG", "666666666")
//                  Log.e("", e)
                }
            }
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
