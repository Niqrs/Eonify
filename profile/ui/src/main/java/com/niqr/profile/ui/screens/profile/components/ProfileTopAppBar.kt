package com.niqr.profile.ui.screens.profile.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.niqr.profile.ui.components.ProfileRippleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopAppBar(
    onMoreClick: () -> Unit
) {
    TopAppBar(
        title = { /*No Title*/ },
        actions = {
            CompositionLocalProvider(LocalRippleTheme provides ProfileRippleTheme) {
                IconButton(onClick = onMoreClick) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = null,
                    )

                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = Color.White
        )
    )
}