package com.niqr.profile.ui.screens.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.niqr.core_ui.theme.EonifyTheme
import com.niqr.profile.ui.R

@Composable
fun BoxScope.ProfileDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    onSetPhotoClick: () -> Unit,
    onLogOutClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(end = 8.dp)
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismissRequest,
            modifier = Modifier.background(EonifyTheme.colorScheme.surface),
        ) {
            DropdownMenuItem(
                text = { Text("Set Profile Photo") },
                onClick = onSetPhotoClick,
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.outline_add_a_photo_24),
                        contentDescription = null
                    )
                }
            )
            DropdownMenuItem(
                text = { Text("Log Out") },
                onClick = onLogOutClick,
                leadingIcon = {
                    Icon(
                        Icons.Outlined.ExitToApp,
                        contentDescription = null
                    )
                }
            )
        }
    }
}