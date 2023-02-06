package com.niqr.profile.ui.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niqr.profile.domain.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repo: ProfileRepository
): ViewModel() {

    var uiState by mutableStateOf(ProfileUiState(repo.user.toUiState()))
        private set

    private val _uiEvent = Channel<ProfileEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAction(event: ProfileAction) {
        when (event) {
            ProfileAction.OnSignOut -> onSignOut()
            ProfileAction.OnOpenBio -> onOpenBio()
        }
    }

    private fun onSignOut() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repo.signOut()
            if (result)
                _uiEvent.send(ProfileEvent.SignOut)
        }
    }

    private fun onOpenBio() {
        viewModelScope.launch {
            _uiEvent.send(ProfileEvent.OpenBio)
        }
    }
}