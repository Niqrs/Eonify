package com.niqr.splash.ui.screens.entry

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.niqr.core_ui.theme.EonifyTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun EntryScreen(
    uiState: EntryUiState,
    onPageChange: (page: Int) -> Unit,
    onNavigateNext: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(pagerState) {
        // Collect from the pager state a snapshotFlow reading the currentPage
        snapshotFlow { pagerState.currentPage }.collect { page ->
            onPageChange(page)
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
            itemSpacing = 32.dp
        ) { pageNumber ->
            val page = uiState.pages[pageNumber]
            Text(
                text = "Page: $pageNumber",
                modifier = Modifier
                    .background(EonifyTheme.colorScheme.primaryContainer)
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
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
        Box(
            modifier = Modifier
                .padding(
                    vertical = 36.dp,
                    horizontal = 12.dp
                )
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
        ) {
            if (uiState.selectedPage != 0) {
                Button(
                    modifier = Modifier
                        .align(CenterStart),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = EonifyTheme.colorScheme.primary,
                        contentColor = EonifyTheme.colorScheme.onPrimary
                    ),
                    onClick = {
//                            onNavigateBack()
                        if (pagerState.canScrollBackward)
                            scope.launch {
                                pagerState.animateScrollToPage(uiState.selectedPage - 1)
                            }
                    }
                ) {
                    Text(text = "Back")
                }
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
            Button(
                modifier = Modifier
                    .align(CenterEnd),
                colors = ButtonDefaults.buttonColors(
                    containerColor = EonifyTheme.colorScheme.primary,
                    contentColor = EonifyTheme.colorScheme.onPrimary
                ),
                onClick = {
                    if (uiState.selectedPage == uiState.pages.lastIndex) {
                        onNavigateNext()
                    } else {
                        if (pagerState.canScrollBackward)
                            scope.launch {
                                pagerState.animateScrollToPage(uiState.selectedPage + 1)
                            }
                    }
                },
            ) {
                Text(text = "Next")
            }
        }
    }
}