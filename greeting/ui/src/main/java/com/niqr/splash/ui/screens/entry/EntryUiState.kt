package com.niqr.splash.ui.screens.entry

import com.niqr.splash.ui.R

data class EntryUiState(
    val pages: List<Page> = com.niqr.splash.ui.screens.entry.pages,
    val selectedPage: Int = 0
)

data class Page(
    val image: Int,
    val title: String,
    val description: String
)

private val pages = listOf(
    Page(
        image = R.drawable.entry_first,
        title = "The Simple Way to\nfind the best! \uD83D\uDC4C",
        description = "Aenean eu lacinia ligula. Quisque eu risus erat. Aenean placerat sollicitudin lectus."
    ),
    Page(
        image = R.drawable.entry_second,
        title = "The Best Design\nStrategy! ✍️",
        description = "Aenean eu lacinia ligula. Quisque eu risus erat. Aenean placerat sollicitudin lectus."
    ),
    Page(
        image = R.drawable.entry_first,
        title = "The Simple Way to\nfind the best! \uD83D\uDC4C",
        description = "Aenean eu lacinia ligula. Quisque eu risus erat. Aenean placerat sollicitudin lectus."
    ),
    Page(
        image = R.drawable.entry_second,
        title = "The Best Design\nStrategy! ✍️",
        description = "Aenean eu lacinia ligula. Quisque eu risus erat. Aenean placerat sollicitudin lectus."
    ),
)
