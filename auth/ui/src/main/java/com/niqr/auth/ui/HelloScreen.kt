package com.niqr.auth.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HelloScreen() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Hello!!!")
    }
}