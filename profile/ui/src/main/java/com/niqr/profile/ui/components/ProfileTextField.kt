package com.niqr.profile.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.niqr.core_ui.theme.EonifyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = EonifyTheme.typography.bodyLarge.copy(textAlign = TextAlign.Start),
        placeholder = { label?.let { Text(text = it) } },
        trailingIcon = trailingIcon,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            textColor = EonifyTheme.colorScheme.textFieldText,
            cursorColor = EonifyTheme.colorScheme.textFieldText,
            unfocusedIndicatorColor = EonifyTheme.colorScheme.textFieldDisabled,
            focusedIndicatorColor = EonifyTheme.colorScheme.textFieldSelectedBorder
        ),
    )
}