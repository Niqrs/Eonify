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

    private val _uiEvent = Channel<ForgotUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ForgotEvent) {
        when(event) {
            ForgotEvent.OnContinueClick -> onContinueClick()
            is ForgotEvent.OnEmailChange -> onEmailChange(event.email)
            ForgotEvent.OnNavigateUp -> onNavigateUp()
        }
    }

    private fun onContinueClick() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(
                ForgotUiEvent.ShowSnackbar("Not yet implemented")
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
            _uiEvent.send(ForgotUiEvent.NavigateUp)
        }
    }
}