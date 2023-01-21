package com.niqr.splash.ui.screens.entry

import com.niqr.splash.ui.R

internal data class EntryUiState(
    val pages: List<Entry> = entries,
    val selectedPage: Int = 0
)

//internal fun EntryUiState.isFirstEntry() = this.entry == entries.first()
//internal fun EntryUiState.isLastEntry() = this.entry == entries.last()

internal data class Entry(
    val image: Int,
    val id: Int,
    val title: String,
    val description: String
)

private val entries = listOf(
    Entry(
        id = 0,
        image = R.drawable.entry_first,
        title = "The Simple Way to\nfind the best! \uD83D\uDC4C",
        description = "Aenean eu lacinia ligula. Quisque eu risus erat. Aenean placerat sollicitudin lectus."
    ),
    Entry(
        id = 1,
        image = R.drawable.entry_second,
        title = "The Best Design\nStrategy! ✍️",
        description = "Aenean eu lacinia ligula. Quisque eu risus erat. Aenean placerat sollicitudin lectus."
    )
)
