package com.niqr.auth.ui.screens.signup.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.niqr.auth.ui.R
import com.niqr.auth.ui.components.HeaderIconContainer

@Composable
fun SignupHeadIcon() {
    HeaderIconContainer( // Icon
        modifier = Modifier.padding(2.dp)
    ) {
        Image(
            modifier = Modifier.size(72.dp),
            painter = painterResource(id = R.drawable.claps),
            contentDescription = null
        )
    }
}