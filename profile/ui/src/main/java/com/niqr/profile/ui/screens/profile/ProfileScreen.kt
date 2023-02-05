package com.niqr.profile.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.niqr.core_ui.theme.EonifyTheme
import com.niqr.profile.ui.R
import com.niqr.profile.ui.components.ProfileRippleTheme
import com.niqr.profile.ui.screens.profile.components.AccountInfoItem
import com.niqr.profile.ui.screens.profile.components.LogOutButton
import com.niqr.profile.ui.screens.profile.components.ProfileTitle
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProfileScreen(
    uiState: ProfileUiState,
    uiEvent: Flow<ProfileEvent>,
    onAction: (ProfileAction) -> Unit,
    onSignOut: () -> Unit
) {
    LaunchedEffect(true) {
        uiEvent.collect {
            when(it) {
                ProfileEvent.SignOut -> onSignOut()
            }
        }
    }

    Box(
        modifier = Modifier
            .background(EonifyTheme.colorScheme.background)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxWidth(),
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .drawWithContent {
                            drawContent()
                            val gradientHeight = size.height * 0.26f
                            drawRect(
                                Brush.verticalGradient(
                                    colors = listOf(Color(0x66000000), Color(0x00000000)),
                                    startY = 0f,
                                    endY = gradientHeight
                                ),
                                topLeft = Offset.Zero,
                                size = Size(size.width, gradientHeight)
                            )
                            val secondGradientY = size.height - gradientHeight
                            drawRect(
                                Brush.verticalGradient(
                                    colors = listOf(Color(0x00000000), Color(0x66000000)),
                                    startY = secondGradientY,
                                    endY = secondGradientY + gradientHeight
                                ),
                                topLeft = Offset(0f, secondGradientY),
                                size = Size(size.width, gradientHeight)
                            )
                        },
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(uiState.user.photoUrl)
                        .error(R.drawable.default_profile_image)
                        .fallback(R.drawable.default_profile_image)
                        .crossfade(true)
                        .build(),
                    placeholder = ColorPainter(Color(0xFFe4e6e7)),
                    contentDescription = "Profile image"
                )

                TopAppBar(
                    title = { /*No Title*/ },
                    modifier = Modifier.padding(top = 24.dp),
                    actions = {
                        CompositionLocalProvider(LocalRippleTheme provides ProfileRippleTheme) {
                            IconButton(onClick = { /*TODO*/ }) {
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

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 14.dp)
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = uiState.user.displayName,
                        style = EonifyTheme.typography.headlineSmall,
                        fontWeight = FontWeight.W500,
                        letterSpacing = 0.2.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }

            Column(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .weight(1f)
            ) {
                ProfileTitle(text = "Account")
                AccountInfoItem(
                    text = uiState.user.email,
                    hint = "Email",
                    onClick = {}
                )
                LogOutButton(
                    onClick = { onAction(ProfileAction.OnSignOut) }
                )
            }
        }
    }
}