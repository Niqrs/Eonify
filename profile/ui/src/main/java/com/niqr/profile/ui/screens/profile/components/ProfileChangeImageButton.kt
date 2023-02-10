package com.niqr.profile.ui.screens.profile.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.niqr.core_ui.theme.EonifyTheme
import com.niqr.profile.ui.R

@Composable
fun BoxScope.ProfileChangeImageButton(
    onClick: () -> Unit
) {
    FilledIconButton(
        onClick = onClick,
        modifier = Modifier
            .padding(end = 16.dp)
            .size(56.dp)
            .align(BottomEndDocked),
        colors = IconButtonDefaults.filledIconButtonColors(
            containerColor = EonifyTheme.colorScheme.primaryContainer,
            contentColor = EonifyTheme.colorScheme.onPrimary
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.add_a_photo_48px),
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .offset(x = 1.5.dp)
        )
    }
}

private val BottomEndDocked: Alignment = Alignment {
        size, space, _ ->
    IntOffset(
        x = space.width - size.width,
        y = space.height - size.height / 2
    )
}