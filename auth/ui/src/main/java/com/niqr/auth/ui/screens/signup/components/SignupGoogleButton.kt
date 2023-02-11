package com.niqr.auth.ui.screens.signup.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.niqr.auth.ui.R
import com.niqr.auth.ui.components.AuthWithButton

@Composable
fun RowScope.SignupGoogleButton(
    onClick: () -> Unit,
    isLoading: Boolean
) {
    AuthWithButton(
        onClick = onClick,
        text = "Google",
        modifier = Modifier.weight(1f),
        enabled = !isLoading,
        icon = {
            Image(
                painter = painterResource(id = R.drawable.ic_google_logo_24dp),
                contentDescription = null,
            )
        },
        horizontalAlignment = Alignment.Start
    )
}