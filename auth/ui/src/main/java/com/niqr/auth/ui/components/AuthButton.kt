package com.niqr.auth.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niqr.core_ui.theme.EonifyTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun AuthButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    loadingText: String = "Submitting...",
//    loadingIcon: @Composable () -> Unit,
    loading: Boolean = false,
    textStyle: TextStyle = EonifyTheme.typography.bodyLarge.copy(
        fontWeight = FontWeight.W500,
        textAlign = TextAlign.Start,
        color = EonifyTheme.colorScheme.onPrimary
    ),
    shape: Shape = RoundedCornerShape(16.dp),
    backgroundColor: Color = EonifyTheme.colorScheme.primary,
    contentPadding: PaddingValues = TextFieldDefaults.textFieldWithoutLabelPadding(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    Button(
        onClick = onClick,
        modifier = modifier.defaultMinSize(48.dp, 48.dp),
        enabled = !loading,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            disabledContainerColor = backgroundColor,
            contentColor = EonifyTheme.colorScheme.textBodyOnSurface,
            disabledContentColor = EonifyTheme.colorScheme.textBodyOnSurface
        ),
        contentPadding = contentPadding,
        interactionSource = interactionSource
    ) {
        AnimatedContent(loading) {
            Text(
                text = if (it) loadingText else text,
                style = textStyle
            )
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 120
)
@Composable
private fun AuthButtonPreview() {
    val (loading, setLoading) = remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthButton(
                onClick = {},
                text = "Button",
                modifier = Modifier.fillMaxWidth(),
                loading = loading
            )
            Button(
                onClick = { setLoading(!loading) }
            ) {
                Text(
                    text = "Loading: $loading"
                )
            }
        }
    }
}