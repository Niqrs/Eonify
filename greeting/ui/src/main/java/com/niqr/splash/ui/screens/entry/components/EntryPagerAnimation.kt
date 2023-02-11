package com.niqr.splash.ui.screens.entry.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.layout.lerp


fun Modifier.entryPagerAnimation(
    pageOffset: Float
) = graphicsLayer {
    // Calculate the absolute offset for the current page from the
    // scroll position. We use the absolute value which allows us to mirror
    // any effects for both directions

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