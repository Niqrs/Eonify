package com.niqr.profile.ui.screens.profile.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.niqr.core_ui.theme.EonifyTheme

@Composable
fun ProfileDivider() {
    Divider(
        modifier = Modifier
            .padding(start = 13.dp)
            .fillMaxWidth(),
        thickness = with(LocalDensity.current) { 1.toDp() },
        color = EonifyTheme.colorScheme.divider.copy(alpha = 0.2f)
    )
}