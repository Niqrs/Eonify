package com.niqr.auth.ui.screens.signup.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.niqr.core_ui.theme.EonifyTheme

@Composable
fun SignupHaveAccount(
    loading: Boolean,
    onClick: () -> Unit
) {
    ClickableText( // Navigate to Sign-In
        text = buildAnnotatedString {
            append("Do you have account? ")
            withStyle(
                style = EonifyTheme.typography.bodyMedium.copy(
                    color = EonifyTheme.colorScheme.textAction,
                    textAlign = TextAlign.Start
                ).toSpanStyle(),
            ) {
                append("Sign In")
            }
        },
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        style = EonifyTheme.typography.bodyMedium.copy(
            color = EonifyTheme.colorScheme.textMediumContrast,
            textAlign = TextAlign.Start
        ),
        onClick = { if (!loading) onClick() }
    )
}