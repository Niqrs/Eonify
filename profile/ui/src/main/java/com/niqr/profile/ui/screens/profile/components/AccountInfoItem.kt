package com.niqr.profile.ui.screens.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.niqr.core_ui.theme.EonifyTheme


@Composable
fun AccountInfoItem(
    text: String,
    hint: String,
    onClick: (() -> Unit)? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(),
                enabled = onClick != null,
                onClick = onClick ?: {}
            ).padding(horizontal = 16.dp, vertical = 6.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            color = EonifyTheme.colorScheme.textMediumContrast
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = hint,
            style = EonifyTheme.typography.titleSmall,
            color = EonifyTheme.colorScheme.textHint
        )
    }
}