package com.niqr.splash.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(

): ViewModel() {

    private val _uiEvent = Channel<SplashUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SplashEvent) {
        when(event) {
            SplashEvent.OnSplashLaunched -> onSplashStarted()
        }
    }

    private fun onSplashStarted() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            _uiEvent.send(SplashUiEvent.OnSplashEnd)
        }
    }
}