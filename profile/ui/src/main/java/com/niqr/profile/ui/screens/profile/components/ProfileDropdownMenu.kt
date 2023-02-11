package com.niqr.profile.ui.screens.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.niqr.core_ui.theme.EonifyTheme
import com.niqr.profile.ui.R

@Composable
fun BoxScope.ProfileDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    onEditNameClick: () -> Unit,
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
            ProfileDropdownMenuItem(
                text = "Edit name",
                icon = Icons.Outlined.Edit,
                onClick = onEditNameClick
            )
            ProfileDropdownMenuItem(
                text = "Set Profile Photo",
                icon = painterResource(id = R.drawable.outline_add_a_photo_24),
                onClick = onSetPhotoClick
            )
            ProfileDropdownMenuItem(
                text = "Log Out",
                icon = Icons.Outlined.ExitToApp,
                onClick = onLogOutClick
            )
        }
    }
}

@Composable
private fun ProfileDropdownMenuItem(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    DropdownMenuItem(
        text = {
            Text(
                text = text,
                color = EonifyTheme.colorScheme.textBodyOnSurface
            )
        },
        onClick = onClick,
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
            )
        },
        colors = MenuDefaults.itemColors(
            leadingIconColor = EonifyTheme.colorScheme.textHint
        )
    )
}

@Composable
private fun ProfileDropdownMenuItem(
    text: String,
    icon: Painter,
    onClick: () -> Unit
) {
    DropdownMenuItem(
        text = {
            Text(
                text = text,
                color = EonifyTheme.colorScheme.textBodyOnSurface
            )
        },
        onClick = onClick,
        leadingIcon = {
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        },
        colors = MenuDefaults.itemColors(
            leadingIconColor = EonifyTheme.colorScheme.textHint
        )
    )
}