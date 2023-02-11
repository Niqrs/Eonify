package com.niqr.auth.ui.screens.signin.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.niqr.auth.ui.components.AuthButton

@Composable
fun SigninAuthButton(
    loading: Boolean,
    onClick: () -> Unit
) {
    AuthButton( // AuthButton
        onClick = onClick,
        text = "Log In",
        modifier = Modifier
            .fillMaxWidth(),
        loading = loading,
    )
}