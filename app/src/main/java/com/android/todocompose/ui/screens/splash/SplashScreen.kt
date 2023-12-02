package com.android.todocompose.ui.screens.splash

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.todocompose.R
import com.android.todocompose.ui.theme.SPLASH_SCREEN_ICON_SIZE
import com.android.todocompose.ui.theme.TodoComposeTheme
import com.android.todocompose.ui.theme.splashScreenBackgroundColor
import com.android.todocompose.util.Constants.SPLASH_SCREEN_DELAY
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToListScreen: () -> Unit
) {

    var startAnimation by remember { mutableStateOf(false) }
    val offSetState by animateDpAsState(
        targetValue = if (startAnimation) 100.dp else 0.dp,
        animationSpec = tween(
            durationMillis = 500
        )
    )
    val alphaState by animateFloatAsState(
        targetValue = if (startAnimation) 0f else 1f,
        animationSpec = tween(
            durationMillis = 500
        )
    )


    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(SPLASH_SCREEN_DELAY)
        navigateToListScreen()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.splashScreenBackgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .size(SPLASH_SCREEN_ICON_SIZE)
                .offset(y = offSetState)
                .alpha(alphaState),
            painter = painterResource(id = R.drawable.todo),
            contentDescription = stringResource(id = R.string.splash_screen),
        )
    }
}

@Composable
@Preview
fun SplashScreenPreviewLightMode() {
    SplashScreen(
        navigateToListScreen = {}
    )
}

@Composable
@Preview
fun SplashScreenPreviewDarkMode() {
    TodoComposeTheme(darkTheme = true) {
        SplashScreen(
            navigateToListScreen = {}
        )
    }
}