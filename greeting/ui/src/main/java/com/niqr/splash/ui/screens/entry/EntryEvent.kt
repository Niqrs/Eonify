package com.niqr.splash.ui.screens.entry

sealed class EntryEvent {
    object OnNavigateNext: EntryEvent()

    object OnNextPage: EntryEvent()
    object OnBackPage: EntryEvent()
}
