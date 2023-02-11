package com.niqr.auth.ui.screens.forgot.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.niqr.auth.ui.components.AuthButton
import com.niqr.core_ui.theme.EonifyTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ForgotContinueButton(
    isSuccess: Boolean,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    AnimatedContent(targetState = isSuccess) {
        if (!it) {
            AuthButton( // ContinueButton
                onClick = onClick,
                text = "Continue",
                modifier = Modifier
                    .fillMaxWidth(),
                loading = isLoading
            )
        } else {
            Text(
                text = "Password reset letter has been sent to email",
                modifier = Modifier.padding(horizontal = 8.dp),
                color = EonifyTheme.colorScheme.textPrimaryHeader,
                textAlign = TextAlign.Center,
                style = EonifyTheme.typography.titleLarge
            )
        }
    }
}