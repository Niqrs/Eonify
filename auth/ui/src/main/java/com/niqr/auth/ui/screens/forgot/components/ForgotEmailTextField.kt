package com.niqr.auth.ui.screens.forgot.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.niqr.auth.ui.components.AuthTextField

@Composable
fun ForgotEmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean
) {
    AuthTextField( // Email TextField
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        enabled = enabled,
        hint = "Email"
    )
}