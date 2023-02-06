package com.niqr.profile.ui.screens.bio

sealed class BioAction {
    object OnNavigateUp: BioAction()
    object OnApply: BioAction()
    data class OnBioChange(val bio: String): BioAction()
}
