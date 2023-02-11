package com.niqr.splash.ui.screens.entry.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.niqr.core_ui.theme.EonifyTheme

@Composable
fun BoxScope.EntryNext(
    onClick: () -> Unit,
    interactionSource: MutableInteractionSource
) {
    Text(
        text = "Next",
        modifier = Modifier
            .align(Alignment.CenterEnd)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        color = EonifyTheme.colorScheme.textBody,
        style = EonifyTheme.typography.bodyLarge
    )
}