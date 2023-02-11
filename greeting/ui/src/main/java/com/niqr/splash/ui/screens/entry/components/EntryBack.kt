package com.niqr.splash.ui.screens.entry.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.niqr.core_ui.theme.EonifyTheme

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BoxScope.EntryBack(
    selectedPage: Int,
    pagerState: PagerState,
    interactionSource: MutableInteractionSource,
    onClick: () -> Unit
) {
    AnimatedVisibility(visible = selectedPage != 0) {
        Text(
            text = "Back",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clickable(
                    enabled = (selectedPage != 0 && pagerState.targetPage != 0),
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClick
                ),
            color = EonifyTheme.colorScheme.textBody,
            style = EonifyTheme.typography.bodyLarge
        )
    }
}