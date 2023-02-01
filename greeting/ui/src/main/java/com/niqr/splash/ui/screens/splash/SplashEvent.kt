package com.niqr.splash.ui.screens.splash

sealed class SplashEvent {
    object OnNavigateToEntry: SplashEvent()
    object OnNavigateToProfile: SplashEvent()
}
