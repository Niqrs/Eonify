package com.niqr.auth.ui.screens.signup.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.niqr.core_ui.theme.EonifyTheme

@Composable
fun SignupPolicy(
    loading: Boolean,
    onClick: () -> Unit
) {
    ClickableText(
        text = buildAnnotatedString {
            append("I’m agree to The ")
            withStyle(
                style = EonifyTheme.typography.bodyMedium.copy(
                    color = EonifyTheme.colorScheme.textAction,
                    textAlign = TextAlign.Start
                ).toSpanStyle(),
            ) {
                append("Terms of Service ")
            }
            append("and ")
            withStyle(
                style = EonifyTheme.typography.bodyMedium.copy(
                    color = EonifyTheme.colorScheme.textAction,
                    textAlign = TextAlign.Start
                ).toSpanStyle(),
            ) {
                append("Privacy Policy")
            }
        },
        modifier = Modifier
            .fillMaxWidth(),
        style = EonifyTheme.typography.bodyMedium.copy(
            color = EonifyTheme.colorScheme.textMediumContrast,
            textAlign = TextAlign.Start
        ),
        onClick = { if (!loading) onClick() }
    )
}