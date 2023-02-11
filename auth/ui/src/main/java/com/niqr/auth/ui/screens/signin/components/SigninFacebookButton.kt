package com.niqr.auth.ui.screens.signin.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.niqr.auth.ui.R
import com.niqr.auth.ui.components.AuthWithButton

@Composable
fun RowScope.SiginFacebookButton(
    onClick: () -> Unit,
    isLoading: Boolean
) {
    AuthWithButton(
        onClick = onClick,
        text = "Facebook",
        modifier = Modifier.weight(1f),
        enabled = !isLoading,
        icon = {
            Image(
                painter = painterResource(id = R.drawable.ic_facebook_logo_24dp),
                contentDescription = null,
            )
        },
        horizontalAlignment = Alignment.Start
    )
}