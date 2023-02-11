package com.niqr.profile.ui.screens.editName

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.None
import arrow.core.Some
import com.niqr.core_util.filterWhitespaces
import com.niqr.profile.domain.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditNameViewModel @Inject constructor(
    private val repo: ProfileRepository
): ViewModel() {

    var uiState by mutableStateOf(EditNameUiState())
        private set

    init {
        viewModelScope.launch {
            repo.userFlow().take(1).collect {
                when(it) {
                    None -> {}
                    is Some -> {
                        initName(it.value.displayName)
                    }
                }
            }
        }
    }

    private val _uiEvent = Channel<EditNameEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAction(action: EditNameAction) {
        when(action) {
            is EditNameAction.OnFirstNameChange -> onFirstNameChange(action.value)
            is EditNameAction.OnOptionalNameChange -> onOptionalNameChange(action.value)
            EditNameAction.OnNavigateUp -> onNavigateUp()
            EditNameAction.OnApply -> onApply()
        }
    }

    private fun onFirstNameChange(value: String) {
        viewModelScope.launch {
            uiState = uiState.copy(
                firstName = value.filterWhitespaces().take(32)
            )
        }
    }

    private fun onOptionalNameChange(value: String) {
        viewModelScope.launch {
            uiState = uiState.copy(
                optionalName = value.filterWhitespaces().take(32)
            )
        }
    }

    private fun onNavigateUp() {
        viewModelScope.launch {
            _uiEvent.send(EditNameEvent.NavigateUp)
        }
    }

    private fun onApply() {
        viewModelScope.launch {
            if (uiState.firstName.isBlank()) {
                _uiEvent.send(EditNameEvent.ShowSnackbar("First name can't be empty"))
            } else {
                val displayName = "${uiState.firstName} ${uiState.optionalName}"
                repo.saveUsername(displayName)
                _uiEvent.send(EditNameEvent.OnApply)
            }
        }
    }

    private fun initName(name: String) {
        val names = name.split(' ')
        uiState = if (names.size > 1) {
            uiState.copy(
                firstName = names[0],
                optionalName = names[1]
            )
        } else {
            uiState.copy(
                firstName = name,
            )
        }
    }
}