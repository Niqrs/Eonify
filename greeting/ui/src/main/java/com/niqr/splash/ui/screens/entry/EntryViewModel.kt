package com.niqr.splash.ui.screens.entry

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
class EntryViewModel @Inject constructor(

): ViewModel() {

    var uiState by mutableStateOf(EntryUiState())

    private val _uiEvent = Channel<EntryUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: EntryEvent) {
        when(event) {
            EntryEvent.OnBackClick -> onBackClick()
            EntryEvent.OnNextClick -> onNextClick()
            is EntryEvent.OnPageChange -> onPageChange(page = event.page)
        }
    }

    private fun onNextClick() {
        if (uiState.selectedPage == uiState.pages.lastIndex) {
            viewModelScope.launch(Dispatchers.IO) {
                _uiEvent.send(EntryUiEvent.OnNavigateNext)
            }
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                _uiEvent.send(EntryUiEvent.OnNextPage)
            }
        }
    }

    private fun onBackClick() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(EntryUiEvent.OnBackPage)
        }
    }

    private fun onPageChange(page: Int) {
        uiState = uiState.copy(
            selectedPage = page
        )
    }
}
