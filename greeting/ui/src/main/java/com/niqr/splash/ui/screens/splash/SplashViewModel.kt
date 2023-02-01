package com.niqr.splash.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    auth: FirebaseAuth
): ViewModel() {
    private val isAuthenticated = auth.currentUser != null

    private val _uiEvent = Channel<SplashEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAction(event: SplashAction) {
        when(event) {
            SplashAction.OnSplashLaunched -> onSplashStarted()
        }
    }

    private fun onSplashStarted() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            _uiEvent.send(
                if (isAuthenticated) SplashEvent.OnNavigateToProfile
                else SplashEvent.OnNavigateToEntry
            )
        }
    }
}