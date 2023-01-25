package com.niqr.auth.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niqr.auth.ui.R
import com.niqr.core_ui.theme.EonifyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthWithButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: @Composable (() -> Unit)? = null,
    textStyle: TextStyle = EonifyTheme.typography.bodyLarge.copy(
        fontWeight = FontWeight.W500,
        textAlign = TextAlign.Start,
        color = EonifyTheme.colorScheme.textBodyOnSurface
    ),
    shape: Shape = RoundedCornerShape(16.dp),
    backgroundColor: Color = EonifyTheme.colorScheme.surface,
    contentPadding: PaddingValues = TextFieldDefaults.textFieldWithoutLabelPadding(),
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    Button(
        onClick = onClick,
        modifier = modifier.defaultMinSize(48.dp, 48.dp),
        enabled = enabled,
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

        Row(
            modifier = Modifier.let {
                if (horizontalAlignment != Alignment.CenterHorizontally) it.fillMaxWidth()
                else it
            },
            horizontalArrangement = Arrangement.spacedBy(16.dp, horizontalAlignment)
        ) {
            icon?.let {
                it()
            }
            Text(
                modifier = Modifier.wrapContentWidth(Alignment.Start),
                text = text,
                style = textStyle
            )
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 100
)
@Composable
private fun AuthWithButtonPreview() {
    Box(
        modifier = Modifier
            .background(EonifyTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AuthWithButton(
                onClick = {},
                text = "Facebook",
                modifier = Modifier.weight(1f),
                icon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_facebook_logo_24dp),
                        contentDescription = null,
                    )
                },
                horizontalAlignment = Alignment.Start
            )
            AuthWithButton(
                onClick = {},
                text = "Google",
                modifier = Modifier.weight(1f),
                icon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_google_logo_24dp),
                        contentDescription = null,
                    )
                },
                horizontalAlignment = Alignment.Start
            )
        }
    }
}