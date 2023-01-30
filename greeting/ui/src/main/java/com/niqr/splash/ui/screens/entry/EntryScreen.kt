package com.niqr.splash.ui.screens.entry

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.layout.lerp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.niqr.core_ui.theme.EonifyTheme
import com.niqr.splash.ui.screens.entry.EntryAction.OnPageChange
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
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
    val scope = rememberCoroutineScope()

    LaunchedEffect(pagerState) {
        // Collect from the pager state a snapshotFlow reading the currentPage
        snapshotFlow { pagerState.currentPage }.collect { page ->
            onAction(OnPageChange(page))
        }
    }

    LaunchedEffect(key1 = true) {
        uiEvent.collect {
            when(it) {
                EntryEvent.OnNavigateNext -> onNavigateNext()
                EntryEvent.OnNextPage -> {
                    if (pagerState.canScrollForward)
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                }
                EntryEvent.OnBackPage -> {
                    if (pagerState.canScrollBackward)
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                }
            }
        }
    }

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
                    .graphicsLayer {
                        // Calculate the absolute offset for the current page from the
                        // scroll position. We use the absolute value which allows us to mirror
                        // any effects for both directions
                        val pageOffset = calculateCurrentOffsetForPage(pageNumber).absoluteValue

                        // We animate the scaleX + scaleY, between 70% and 100%
                        lerp(
                            start = ScaleFactor(0.7f, 0.7f),
                            stop = ScaleFactor(1f, 1f),
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale.scaleX
                            scaleY = scale.scaleY
                        }

                        // We animate the alpha, between 0% and 100%
                        alpha = lerp(
                            start = ScaleFactor(-0.25f, -0.25f),
                            stop = ScaleFactor(1f, 1f),
                            fraction = 1f - pageOffset.coerceIn(-0.25f, 1f)
                        ).scaleX
                    }
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = page.image),
                    contentDescription = "image",
                    modifier = Modifier
                        .padding(24.dp)
                        .aspectRatio(1f)
                )

                Text(
                    text = page.title,
                    modifier = Modifier.padding(top = 32.dp),
                    color = EonifyTheme.colorScheme.textContrast,
                    style = EonifyTheme.typography.headlineSmall
                )

                Text(
                    text = page.description,
                    modifier = Modifier.padding(top = 32.dp),
                    color = EonifyTheme.colorScheme.textBody,
                    style = EonifyTheme.typography.bodyLarge
                )
            }
        }

        val interactionSource = remember { MutableInteractionSource() }
        Box(
            modifier = Modifier
                .padding(
                    vertical = 36.dp,
                    horizontal = 28.dp
                )
                .align(BottomCenter)
                .fillMaxWidth(),
        ) {
            AnimatedVisibility(visible = uiState.selectedPage != 0) {
                Text(
                    text = "Back",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clickable(
                            enabled = (uiState.selectedPage != 0 && pagerState.targetPage != 0),
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            onAction(EntryAction.OnBackClick)
                        },
                    color = EonifyTheme.colorScheme.textBody,
                    style = EonifyTheme.typography.bodyLarge
                )
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = EonifyTheme.colorScheme.primary,
                inactiveColor = EonifyTheme.colorScheme.primaryContainer,
                indicatorWidth = 32.dp,
                indicatorHeight = 6.dp,
                spacing = 12.dp,
                modifier = Modifier
                    .align(Center)
                    .padding(16.dp)
            )

            Text(
                text = "Next",
                modifier = Modifier
                    .align(CenterEnd)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        onAction(EntryAction.OnNextClick)
                    },
                color = EonifyTheme.colorScheme.textBody,
                style = EonifyTheme.typography.bodyLarge
            )
        }
    }
}