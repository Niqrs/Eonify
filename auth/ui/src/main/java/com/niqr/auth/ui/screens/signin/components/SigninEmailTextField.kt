package com.niqr.auth.ui.screens.signin.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.niqr.auth.ui.components.AuthTextField

@Composable
fun SigninEmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    loading: Boolean
) {
    AuthTextField( // Email TextField
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        enabled = !loading,
        hint = "Email"
    )
}