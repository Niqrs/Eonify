package com.niqr.profile.ui.screens.editName

sealed class EditNameAction {
    data class OnFirstNameChange(val value: String): EditNameAction()
    data class OnOptionalNameChange(val value: String): EditNameAction()
    object OnNavigateUp: EditNameAction()
    object OnApply: EditNameAction()
}