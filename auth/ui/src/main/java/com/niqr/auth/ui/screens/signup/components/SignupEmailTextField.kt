package com.niqr.auth.ui.screens.signup.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.niqr.auth.ui.components.AuthTextField

@Composable
fun SignupEmailTextField(
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