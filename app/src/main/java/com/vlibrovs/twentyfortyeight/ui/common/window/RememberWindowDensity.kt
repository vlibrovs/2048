package com.vlibrovs.twentyfortyeight.ui.common.window

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun rememberWindowInfo(): WindowDensity {
    val configuration = LocalConfiguration.current
    return WindowDensity(
        screenWidthInfo = when {
            configuration.screenWidthDp < 600 -> WindowDensity.WindowType.Compact
            configuration.screenWidthDp < 840 -> WindowDensity.WindowType.Medium
            else -> WindowDensity.WindowType.Expanded
        },
        screenHeightInfo = when {
            configuration.screenHeightDp < 480 -> WindowDensity.WindowType.Compact
            configuration.screenHeightDp < 900 -> WindowDensity.WindowType.Medium
            else -> WindowDensity.WindowType.Expanded
        },
        screenWidth = configuration.screenWidthDp.dp,
        screenHeight = configuration.screenHeightDp.dp
    )
}

data class WindowDensity(
    val screenWidthInfo: WindowType,
    val screenHeightInfo: WindowType,
    val screenWidth: Dp,
    val screenHeight: Dp
) {
    sealed class WindowType() {
        object Compact : WindowType()
        object Medium : WindowType()
        object Expanded : WindowType()
    }
}