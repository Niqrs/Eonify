package com.niqr.splash.ui.screens.entry.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.niqr.core_ui.theme.EonifyTheme

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BoxScope.EntryPagerIndicator(
    pagerState: PagerState
) {
    HorizontalPagerIndicator(
        pagerState = pagerState,
        activeColor = EonifyTheme.colorScheme.primary,
        inactiveColor = EonifyTheme.colorScheme.primaryContainer,
        indicatorWidth = 32.dp,
        indicatorHeight = 6.dp,
        spacing = 12.dp,
        modifier = Modifier
            .align(Alignment.Center)
            .padding(16.dp)
    )
}