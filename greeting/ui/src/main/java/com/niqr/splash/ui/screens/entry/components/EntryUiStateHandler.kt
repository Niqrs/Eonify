package com.niqr.splash.ui.screens.entry.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.niqr.splash.ui.screens.entry.EntryAction
import com.niqr.splash.ui.screens.entry.EntryEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun EntryUiStateHandler(
    pagerState: PagerState,
    uiEvent: Flow<EntryEvent>,
    onAction: (EntryAction) -> Unit,
    onNavigateNext: () -> Unit
) {
    val scope = rememberCoroutineScope()

    LaunchedEffect(pagerState) {
        // Collect from the pager state a snapshotFlow reading the currentPage
        snapshotFlow { pagerState.currentPage }.collect { page ->
            onAction(EntryAction.OnPageChange(page))
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
}