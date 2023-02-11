package com.niqr.auth.ui.screens.signup.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.niqr.auth.ui.components.AuthTextField

@Composable
fun RowScope.SignupFirstNameTextField(
    value: String,
    onValueChange: (String) -> Unit,
    loading: Boolean
) {
    AuthTextField( // First Name TextField
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.weight(1f),
        enabled = !loading,
        hint = "First Name"
    )
}