package com.niqr.auth.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niqr.core_ui.theme.EonifyTheme

@Composable
fun AuthCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    backgroundColor: Color = EonifyTheme.colorScheme.surface,
    checkmarkColor: Color = EonifyTheme.colorScheme.primary,
) {
    val colors = CheckboxDefaults.colors(
        checkedColor = backgroundColor,
        uncheckedColor = backgroundColor,
        checkmarkColor = checkmarkColor,
        disabledCheckedColor = backgroundColor,
        disabledUncheckedColor = backgroundColor
    )

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(EonifyTheme.colorScheme.surface),
        contentAlignment = Alignment.Center
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier
                .size(26.dp),
            enabled = enabled,
            colors = colors
        )
    }
}

@Preview(
    widthDp = 50,
    heightDp = 50
)
@Composable
private fun AuthCheckboxPreview() {
    val (checked, onCheckedChange) = remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .background(EonifyTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        AuthCheckbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}