package com.niqr.core_ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
internal val Typography = Typography(
        bodyLarge = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.5.sp,
                textAlign = TextAlign.Center
        ),
        bodyMedium = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 14.5.sp,
                lineHeight = 22.sp,
                letterSpacing = 0.4.sp,
                textAlign = TextAlign.Center
        ),


        headlineMedium = TextStyle(
                fontWeight = FontWeight.W800,
                fontSize = 36.sp,
                letterSpacing = 1.sp
        ),
        headlineSmall = TextStyle(
                fontWeight = FontWeight.W800,
                fontSize = 28.sp,
                lineHeight = 48.sp,
                letterSpacing = 1.sp,
                textAlign = TextAlign.Center
        ),


        titleLarge = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W500,
                fontSize = 22.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.65.sp,
        ),
        titleMedium = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W500,
                fontSize = 17.sp,
                lineHeight = 19.sp,
                letterSpacing = 0.3.sp,
        ),
        titleSmall = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.25.sp,
        )

        /* Other default text styles to override
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

internal val LocalTypography = staticCompositionLocalOf { Typography }