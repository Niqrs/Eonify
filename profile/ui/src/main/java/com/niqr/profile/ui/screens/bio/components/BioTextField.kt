package com.niqr.profile.ui.screens.bio.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.niqr.core_ui.components.AnimatedCounter
import com.niqr.core_ui.theme.EonifyTheme
import com.niqr.profile.ui.components.ProfileTextField

@Composable
fun BioTextField(
    value: String,
    onValueChange: (String) -> Unit,
    charactersLeft: Int
) {
    ProfileTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = "Bio",
        trailingIcon = {
            AnimatedCounter(
                count = charactersLeft,
                style = EonifyTheme.typography.bodyLarge.copy(color = EonifyTheme.colorScheme.textFieldHint)
            )
        }
    )
}