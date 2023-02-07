package com.niqr.core_ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.SystemUiController
import com.niqr.core_ui.theme.EonifyTheme.typography

@Composable
fun EonifyTheme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        LocalTypography provides typography,
    ) {
        ProvideTextStyle(value = typography.bodyLarge, content = content)
    }
}

object EonifyTheme {
    val colorScheme: EonifyColorScheme
        @Composable
        get() = LocalColorScheme.current

    val typography: Typography
        @Composable
        get() = LocalTypography.current
}

fun SystemUiController.setDefaultStatusBarColor(darkTheme: Boolean) {
    this.setStatusBarColor(
        color = Color.Transparent,
        darkIcons = !darkTheme
    )
}