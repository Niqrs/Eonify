package com.niqr.auth.ui.screens.forgot

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.niqr.auth.ui.handlers.ForgotEmailHandler
import com.niqr.core_util.filterWhitespaces
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotViewModel @Inject constructor(
    private val handler: ForgotEmailHandler
): ViewModel() {

    var uiState by mutableStateOf(ForgotUiState())
        private set

    private val _uiEvent = Channel<ForgotEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAction(event: ForgotAction) {
        when(event) {
            ForgotAction.OnNavigateUp -> onNavigateUp()
            is ForgotAction.OnEmailChange -> onEmailChange(event.email)
            ForgotAction.OnContinueClick -> onContinueClick()
        }
    }

    private fun onNavigateUp() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(ForgotEvent.NavigateUp)
        }
    }

    private fun onEmailChange(email: String) {
        uiState = uiState.copy(
            email = email.filterWhitespaces().take(64)
        )
    }

    private fun onContinueClick() {
        if (uiState.isLoading) return
        uiState = uiState.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            if (checkEmail())
                sendEmail()
            uiState = uiState.copy(isLoading = false)
        }
    }

    private suspend fun checkEmail(): Boolean {
        return if (uiState.email.isBlank()) {
            _uiEvent.send(ForgotEvent.ShowSnackbar("Fields can't be empty"))
            false
        } else {
            true
        }
    }

    private suspend fun sendEmail() {
        when(val result = handler.sendEmail(uiState.email)) {
            is Either.Right -> {
                uiState = uiState.copy(isSuccess = true)
            }
            is Either.Left -> _uiEvent.send(ForgotEvent.ShowSnackbar(result.value))
        }
    }
}