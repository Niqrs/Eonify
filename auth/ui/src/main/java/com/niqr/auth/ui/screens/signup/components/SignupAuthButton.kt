package com.niqr.auth.ui.screens.signup.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.niqr.auth.ui.components.AuthButton

@Composable
fun SignupAuthButton(
    loading: Boolean,
    onClick: () -> Unit
) {
    AuthButton( // AuthButton
        onClick = onClick,
        text = "Create Account",
        modifier = Modifier
            .fillMaxWidth(),
        loading = loading
    )
}