package com.niqr.profile.ui.screens.profile.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.niqr.core_ui.theme.EonifyTheme

@Composable
fun BoxScope.ProfileUsername(
    name: String
) {
    Column(
        modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(horizontal = 16.dp)
            .padding(bottom = 14.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = name,
            style = EonifyTheme.typography.headlineSmall,
            fontWeight = FontWeight.W500,
            letterSpacing = 0.2.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}