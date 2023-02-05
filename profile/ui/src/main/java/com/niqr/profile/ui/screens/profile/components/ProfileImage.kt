package com.niqr.profile.ui.screens.profile.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.niqr.profile.ui.R

@Composable
fun ProfileImage(
    photoUrl: String
) {
    AsyncImage(
        modifier = Modifier
            .fillMaxSize()
            .drawImageShadow(),
        model = imageModel(photoUrl),
        placeholder = ColorPainter(Color(0xFFe4e6e7)),
        contentDescription = "Profile image"
    )
}

@Composable
private fun imageModel(url: String) = ImageRequest.Builder(LocalContext.current)
    .data(url)
    .error(R.drawable.default_profile_image)
    .fallback(R.drawable.default_profile_image)
    .crossfade(true)
    .build()

private fun Modifier.drawImageShadow() =
    drawWithContent {
        drawContent()
        val gradientHeight = size.height * 0.26f
        drawRect(
            Brush.verticalGradient(
                colors = listOf(Color(0x66000000), Color(0x00000000)),
                startY = 0f,
                endY = gradientHeight
            ),
            topLeft = Offset.Zero,
            size = Size(size.width, gradientHeight)
        )
        val secondGradientY = size.height - gradientHeight
        drawRect(
            Brush.verticalGradient(
                colors = listOf(Color(0x00000000), Color(0x66000000)),
                startY = secondGradientY,
                endY = secondGradientY + gradientHeight
            ),
            topLeft = Offset(0f, secondGradientY),
            size = Size(size.width, gradientHeight)
        )
    }