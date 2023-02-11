package com.niqr.profile.ui.screens.editName.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.niqr.profile.ui.components.ProfileTextField

@Composable
fun OptionalNameTextField(
    value: String,
    onValueChange: (String) -> Unit,
) {
    ProfileTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = "Optional name",
    )
}