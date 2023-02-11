package com.niqr.auth.ui.screens.signin.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.niqr.core_ui.theme.EonifyTheme

@Composable
fun SigninForgotPassword(
    loading: Boolean,
    onClick: () -> Unit
) {
    ClickableText( // Navigate to Forgot
        text = AnnotatedString("Forgot Password?"),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        style = EonifyTheme.typography.bodyMedium.copy(
            color = EonifyTheme.colorScheme.textHint,
            textAlign = TextAlign.End
        ),
        onClick = { if (!loading) onClick() }
    )
}