package com.niqr.profile.ui.screens.profile.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.niqr.core_ui.theme.EonifyTheme

@Composable
fun LogOutButton(
    text: String = "Log Out",
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.BottomCenter),
        colors = ButtonDefaults.buttonColors(
            contentColor = EonifyTheme.colorScheme.onPrimary,
            containerColor = EonifyTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(10.dp),
        onClick = onClick,
    ) {
        Text(text = text)
    }
}