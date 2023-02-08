package com.niqr.profile.ui.screens.bio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niqr.profile.domain.ProfileRepository
import com.niqr.profile.ui.screens.profile.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BioViewModel @Inject constructor(
    private val repo: ProfileRepository
): ViewModel() {

    var uiState by mutableStateOf(BioUiState())
        private set

    init {
        viewModelScope.launch {
            repo.userFlow().take(1).collect {
                it?.let {
                    onBioChange(it.toUiState().bio)
                }
            }
        }
    }

    private val _uiEvent = Channel<BioEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAction(event: BioAction) {
        when(event) {
            BioAction.OnApply -> onApply()
            BioAction.OnNavigateUp -> onNavigateUp()
            is BioAction.OnBioChange -> onBioChange(event.bio)
        }
    }

    private fun onApply() {
        viewModelScope.launch {
            repo.saveBio(uiState.bio)
            _uiEvent.send(BioEvent.OnApply)
        }
    }

    private fun onNavigateUp() {
        viewModelScope.launch {
            _uiEvent.send(BioEvent.NavigateUp)
        }
    }

    private fun onBioChange(bio: String) {
        val charactersLeft = bioCharactersLimit - bio.length
        if (charactersLeft < 0) return
        uiState = uiState.copy(bio = bio, charactersLeft = charactersLeft)
    }
}

private const val bioCharactersLimit: Int = 100