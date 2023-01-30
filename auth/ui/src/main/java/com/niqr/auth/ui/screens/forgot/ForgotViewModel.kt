package com.niqr.auth.ui.screens.forgot

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
class ForgotViewModel @Inject constructor(

): ViewModel() {

    var uiState by mutableStateOf(ForgotUiState())
        private set

    private val _uiEvent = Channel<ForgotEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAction(event: ForgotAction) {
        when(event) {
            ForgotAction.OnContinueClick -> onContinueClick()
            is ForgotAction.OnEmailChange -> onEmailChange(event.email)
            ForgotAction.OnNavigateUp -> onNavigateUp()
        }
    }

    private fun onContinueClick() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(
                ForgotEvent.ShowSnackbar("Not yet implemented")
            )
        }
    }

    private fun onEmailChange(email: String) {
        uiState = uiState.copy(
            email = email
        )
    }

    private fun onNavigateUp() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(ForgotEvent.NavigateUp)
        }
    }
}