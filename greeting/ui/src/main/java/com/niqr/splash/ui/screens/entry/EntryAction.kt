package com.niqr.splash.ui.screens.entry

sealed class EntryAction {
    object OnNextClick: EntryAction()
    object OnBackClick: EntryAction()
    data class OnPageChange(val page: Int): EntryAction()
}
