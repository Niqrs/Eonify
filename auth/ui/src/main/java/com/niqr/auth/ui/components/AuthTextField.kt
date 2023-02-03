package com.niqr.auth.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niqr.core_ui.theme.EonifyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textStyle: TextStyle = EonifyTheme.typography.bodyLarge.copy(textAlign = TextAlign.Start),
    hint: String,
    error: Boolean = false,
    icon: @Composable (() -> Unit)? = null,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = AuthTextFieldDefaults.textFieldShape(),
    colors: AuthTextFieldColors = AuthTextFieldDefaults.textFieldColors()
) {
    val isFocused = interactionSource.collectIsFocusedAsState().value

    val contentColor by animateColorAsState(
        if (enabled) colors.contentColor
        else colors.disabledContentColor
    )
    val iconColor by animateColorAsState(
        if (value.isEmpty() && !isFocused) colors.hintColor
        else contentColor
    )

    val indicatorSize by animateDpAsState(targetValue = if (isFocused || error) 1.5.dp else 0.dp)
    val indicatorColor by animateColorAsState(
        targetValue =
        if (error) colors.errorIndicatorColor
        else if (isFocused) colors.focusedIndicatorColor
        else colors.unfocusedIndicatorColor
    )

    val mergedTextStyle = textStyle.merge(
        TextStyle(color = contentColor)
    )

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .defaultMinSize(
                minWidth = TextFieldDefaults.MinWidth,
            ),
        enabled = enabled,
        textStyle = mergedTextStyle,
        interactionSource = interactionSource,
        singleLine = singleLine,
        visualTransformation = visualTransformation,
        cursorBrush = SolidColor(contentColor),
        decorationBox = {
            Box(
                modifier = Modifier
                    .clip(shape)
                    .border(indicatorSize, indicatorColor, shape)
                    .background(color = colors.backgroundColor),
                content = {
                    Box(
                        modifier = Modifier
                            .padding(
                                TextFieldDefaults.textFieldWithoutLabelPadding(
                                    end = if (icon == null) 16.dp else 52.dp
                                )
                            ),
                    ) {
                        AnimatedVisibility( //Hint
                            visible = value.isEmpty() && !isFocused,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            Text(
                                text = hint,
                                style = textStyle.copy(color = colors.hintColor)
                            )
                        }
                        it() //Text field
                    }
                    if (icon != null) //Icon
                        Box(
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .defaultMinSize(48.dp, 48.dp)
                                .align(Alignment.CenterEnd),
                            contentAlignment = Alignment.Center
                        ) {
                            CompositionLocalProvider(
                                LocalContentColor provides iconColor,
                            ) {
                                icon()
                            }
                        }
                }
            )
        }
    )
}

@Preview(
    widthDp = 360
)
@Composable
private fun AuthTextFieldPreview() { //Preview for TextField Tests
    val (text, setText) = remember { mutableStateOf("") }
    var enabled by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current


    Box(
        modifier = Modifier
            .background(EonifyTheme.colorScheme.background)
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthTextField(
                value = text,
                onValueChange = setText,
                enabled = enabled,
                hint = "Write text",
                error = error,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null
                    )
                }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        focusManager.clearFocus()
                    }
                ) {
                    Text(
                        text = "Unfocus"
                    )
                }
                Button(
                    onClick = {
                        error = !error
                    }
                ) {
                    Text(text = if (!error) "Crash" else "Fix")
                }
                Button(
                    onClick = {
                        focusManager.clearFocus()
                        enabled = !enabled
                    }
                ) {
                    Text(text = if (enabled) "Enabled" else "Disabled")
                }
            }
        }
    }
}


object AuthTextFieldDefaults {

    @Composable
    fun textFieldColors(
        contentColor: Color = EonifyTheme.colorScheme.textFieldText,
        backgroundColor: Color = EonifyTheme.colorScheme.surface,
        disabledContentColor: Color = EonifyTheme.colorScheme.textFieldDisabled,
        focusedIndicatorColor: Color = EonifyTheme.colorScheme.primary,
        unfocusedIndicatorColor: Color = Color.Transparent,
        errorIndicatorColor: Color = EonifyTheme.colorScheme.error,
        hintColor: Color = EonifyTheme.colorScheme.textFieldHint
    ): AuthTextFieldColors =
        AuthTextFieldColors(
            contentColor = contentColor,
            backgroundColor = backgroundColor,
            disabledContentColor = disabledContentColor,
            focusedIndicatorColor = focusedIndicatorColor,
            unfocusedIndicatorColor = unfocusedIndicatorColor,
            errorIndicatorColor = errorIndicatorColor,
            hintColor = hintColor,
        )

    @Composable
    fun textFieldShape(): Shape =
        RoundedCornerShape(16.dp)
}

data class AuthTextFieldColors internal constructor(
    val contentColor: Color,
    val disabledContentColor: Color,
    val backgroundColor: Color,
    val focusedIndicatorColor: Color,
    val unfocusedIndicatorColor: Color,
    val errorIndicatorColor: Color,
    val hintColor: Color,
)
