package com.niqr.splash.ui.screens.entry

sealed class EntryEvent {
    object OnNextClick: EntryEvent()
    object OnBackClick: EntryEvent()
    data class OnPageChange(val page: Int): EntryEvent()
}
