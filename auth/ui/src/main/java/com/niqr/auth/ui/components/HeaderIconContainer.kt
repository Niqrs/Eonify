package com.niqr.auth.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niqr.auth.ui.R
import com.niqr.core_ui.theme.EonifyTheme

@Composable
fun HeaderIconContainer(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(20.dp),
    backgroundColor: Color = EonifyTheme.colorScheme.surface,
    icon: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(shape)
            .background(backgroundColor)
            .defaultMinSize(82.dp, 82.dp)
            .padding(12.dp)
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        icon()
    }
}

@Preview(
    widthDp = 200,
    heightDp = 200
)
@Composable
fun HeaderIconContainerPreview() {
    Box(
        modifier = Modifier
            .background(EonifyTheme.colorScheme.background)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        HeaderIconContainer(
            modifier = Modifier.padding(2.dp)
        ) {
            Image(
                modifier = Modifier.size(72.dp),
                painter = painterResource(id = R.drawable.claps),
                contentDescription = null
            )
        }
    }
}