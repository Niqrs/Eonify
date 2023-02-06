package com.niqr.profile.ui.screens.bio

sealed class BioEvent {
    object NavigateUp: BioEvent()
    object OnApply: BioEvent()
}
