package com.niqr.profile.ui.screens.profile

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
class ProfileViewModel @Inject constructor(

): ViewModel() {

    var uiState by mutableStateOf(ProfileUiState())
        private set

    private val _uiEvent = Channel<ProfileEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAction(event: ProfileAction) {
        when (event) {
            ProfileAction.OnSignOut -> onSignOut()
        }
    }

    private fun onSignOut() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(ProfileEvent.SignOut)
        }
    }
}