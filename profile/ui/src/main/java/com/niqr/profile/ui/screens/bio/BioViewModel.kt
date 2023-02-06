package com.niqr.profile.ui.screens.bio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class BioViewModel @Inject constructor(

): ViewModel() {

    var uiState by mutableStateOf(BioUiState())
        private set

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
        TODO("Not yet implemented")
    }

    private fun onNavigateUp() {
        TODO("Not yet implemented")
    }

    private fun onBioChange(bio: String) {
        TODO("Not yet implemented")
    }
}

private const val bioCharactersLimit: Int = 100