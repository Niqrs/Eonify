package com.niqr.profile.ui.screens.editName

sealed class EditNameEvent {
    object NavigateUp: EditNameEvent()
    object OnApply: EditNameEvent()
    data class ShowSnackbar(val message: String): EditNameEvent()
}