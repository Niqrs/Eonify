package com.niqr.splash.ui.screens.entry.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun EntryPageImage(
    image: Int
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "image",
        modifier = Modifier
            .padding(24.dp)
            .aspectRatio(1f)
    )
}