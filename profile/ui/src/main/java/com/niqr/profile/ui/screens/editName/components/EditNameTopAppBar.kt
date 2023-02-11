package com.niqr.profile.ui.screens.editName.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.niqr.core_ui.theme.EonifyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNameTopAppBar(
    onNavigateUp: () -> Unit,
    onApply: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.shadow(5.dp),
        title = { Text(text = "Edit name") },
        navigationIcon = {
            IconButton(onClick = onNavigateUp) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Navigate back"
                )
            }
        },
        actions = {
            IconButton(onClick = onApply) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Apply"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = EonifyTheme.colorScheme.topAppBar,
            titleContentColor = EonifyTheme.colorScheme.textContrast,
            navigationIconContentColor = EonifyTheme.colorScheme.textContrast,
            actionIconContentColor = EonifyTheme.colorScheme.textContrast
        ),
    )
}