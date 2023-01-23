package com.niqr.splash.ui.screens.entry

sealed class EntryUiEvent {
    object OnNavigateNext: EntryUiEvent()

    object OnNextPage: EntryUiEvent()
    object OnBackPage: EntryUiEvent()
}
