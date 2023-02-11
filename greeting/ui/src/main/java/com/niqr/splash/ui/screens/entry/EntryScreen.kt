package com.niqr.splash.ui.screens.entry

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.niqr.splash.ui.screens.entry.components.EntryBack
import com.niqr.splash.ui.screens.entry.components.EntryNext
import com.niqr.splash.ui.screens.entry.components.EntryPageDescription
import com.niqr.splash.ui.screens.entry.components.EntryPageImage
import com.niqr.splash.ui.screens.entry.components.EntryPageTitle
import com.niqr.splash.ui.screens.entry.components.EntryPagerIndicator
import com.niqr.splash.ui.screens.entry.components.EntryUiStateHandler
import com.niqr.splash.ui.screens.entry.components.entryPagerAnimation
import kotlinx.coroutines.flow.Flow
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun EntryScreen(
    uiState: EntryUiState,
    uiEvent: Flow<EntryEvent>,
    onAction: (EntryAction) -> Unit,
    onNavigateNext: () -> Unit,
) {
    val pagerState = rememberPagerState()

    EntryUiStateHandler(
        pagerState = pagerState,
        uiEvent = uiEvent,
        onAction = onAction,
        onNavigateNext = onNavigateNext
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            count = uiState.pages.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize(),
            itemSpacing = 64.dp
        ) { pageNumber ->
            val page = uiState.pages[pageNumber]
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .entryPagerAnimation(
                        pageOffset = calculateCurrentOffsetForPage(pageNumber).absoluteValue
                    )
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EntryPageImage(
                    image = page.image
                )

                EntryPageTitle(
                    title = page.title
                )

                EntryPageDescription(
                    description = page.description
                )
            }
        }

        Box(
            modifier = Modifier
                .padding(
                    vertical = 36.dp,
                    horizontal = 28.dp
                )
                .align(BottomCenter)
                .fillMaxWidth(),
        ) {
            val interactionSource = remember { MutableInteractionSource() }

            EntryBack(
                selectedPage = uiState.selectedPage,
                pagerState = pagerState,
                interactionSource = interactionSource,
                onClick = { onAction(EntryAction.OnBackClick) }
            )

            EntryPagerIndicator(
                pagerState = pagerState
            )

            EntryNext(
                onClick = { onAction(EntryAction.OnNextClick) },
                interactionSource = interactionSource
            )
        }
    }
}