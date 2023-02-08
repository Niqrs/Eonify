package com.niqr.core_ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val LightColorScheme = EonifyColorScheme(
    primary = Primary50,
    onPrimary = Color.White,
    primaryContainer = Primary10,
    surface = Primary5,
    background = Color.White,
    onBackground = Gray90,
    topAppBar = Color.White,

    divider = Gray20,

    textPrimaryHeader = Primary50,
    textHeader = Gray90,
    textBody = Gray70,
    textBodyOnSurface = Gray70,
    textContrast = Gray90,
    textMediumContrast = Gray80,
    textHint = Gray60,
    textAction = Primary50,

    textFieldText = Gray90,
    textFieldHint = Gray60,
    textFieldDisabled = Gray70,
    textFieldSelectedBorder = Primary50,

    error = Color.Red
)

internal val DarkColorScheme = EonifyColorScheme(
    primary = Primary50,
    onPrimary = Color.White,
    primaryContainer = Primary30,
    surface = Gray80,
    background = Gray90,
    onBackground = Gray5,
    topAppBar = Gray85,

    divider = Gray40,

    textPrimaryHeader = Primary50,
    textHeader = Gray5,
    textBody = Gray30,
    textBodyOnSurface = Gray10,
    textContrast = Gray5,
    textMediumContrast = Gray10,
    textHint = Gray40,
    textAction = Primary40,

    textFieldText = Gray5,
    textFieldHint = Gray40,
    textFieldDisabled = Gray30,
    textFieldSelectedBorder = Primary40,

    error = Color.Red
)

data class EonifyColorScheme(
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val surface: Color,
    val background: Color,
    val onBackground: Color,
    val topAppBar: Color,

    val divider: Color,

    val textPrimaryHeader: Color,
    val textHeader: Color,
    val textBody: Color,
    val textBodyOnSurface: Color,
    val textContrast: Color,
    val textMediumContrast: Color,
    val textHint: Color,
    val textAction: Color,

    val textFieldText: Color,
    val textFieldHint: Color,
//    val textFieldBorder: Color,
    val textFieldDisabled: Color,
    val textFieldSelectedBorder: Color,

    val error: Color
)

internal val LocalColorScheme = staticCompositionLocalOf { LightColorScheme }