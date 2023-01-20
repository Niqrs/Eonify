package com.niqr.splash.ui.screens.entry

internal data class EntryUiState(
    val entry: Entry = entries.first()
)

internal fun EntryUiState.isFirstEntry() = this.entry == entries.first()
internal fun EntryUiState.isLastEntry() = this.entry == entries.last()

internal data class Entry(
//    val image: Image???
    val id: Int,
    val title: String,
    val description: String
)

internal val entries = listOf(
    Entry(
        id = 0,
        title = "Title1",
        description = "Description1"
    ),
    Entry(
        id = 1,
        title = "Title2",
        description = "Description2"
    )
)
