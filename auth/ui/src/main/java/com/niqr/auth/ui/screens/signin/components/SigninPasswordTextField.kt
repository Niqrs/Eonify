package com.niqr.auth.ui.screens.signin.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.with
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.niqr.auth.ui.R
import com.niqr.auth.ui.components.AuthTextField

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SigninPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    passwordVisible: Boolean,
    onPasswordVisibilityChange: (Boolean) -> Unit,
    loading: Boolean
) {
    AuthTextField( // Password TextField
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        enabled = !loading,
        hint = "Password",
        visualTransformation = if (passwordVisible) VisualTransformation.None
        else PasswordVisualTransformation(),
        icon = {
            IconButton(
                onClick = { onPasswordVisibilityChange(!passwordVisible) },
                enabled = !loading,
                colors = IconButtonDefaults.iconButtonColors(disabledContentColor = LocalContentColor.current)
            ) {
                AnimatedContent(
                    targetState = passwordVisible,
                    transitionSpec = {
                        fadeIn(animationSpec = tween(220, delayMillis = 30)) +
                                scaleIn(
                                    initialScale = 0.92f,
                                    animationSpec = tween(220, delayMillis = 30)
                                ) with
                                fadeOut(animationSpec = tween(220))
                    }
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (it) R.drawable.outline_visibility_24
                            else R.drawable.outline_visibility_off_24
                        ),
                        contentDescription = null
                    )
                }
            }
        }
    )
}