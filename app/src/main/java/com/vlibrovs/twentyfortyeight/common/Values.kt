package com.vlibrovs.twentyfortyeight.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vlibrovs.twentyfortyeight.ui.common.window.WindowInfo

fun getValues(windowType: WindowInfo.WindowType): Values {
    return when (windowType) {
        WindowInfo.WindowType.Compact -> Values.Compact
        WindowInfo.WindowType.Expanded -> Values.Expanded
        WindowInfo.WindowType.Medium -> Values.Medium
    }
}

sealed class Values {
    abstract val mainMenuTitleSize: TextUnit
    abstract val buttonTextSize: TextUnit
    abstract val mainMenuIconSize: Dp
    abstract val mainMenuPadding: PaddingValues
    abstract val mainMenuHeight: Dp
    abstract val buttonHeight: Dp
    abstract val mainMenuTitlePadding: PaddingValues
    abstract val defaultTitleFontSize: TextUnit
    abstract val boxCornerRadius: Dp
    abstract val statsPadding: PaddingValues
    abstract val contentFontSize: TextUnit
    abstract val lineWidth: Dp
    abstract val statsTableHeight: Dp
    abstract val gamesStatsBoxHeight: Dp
    abstract val gameTitleFontSize: TextUnit
    abstract val gamePadding: PaddingValues
    abstract val settingsInboxPadding: PaddingValues
    abstract val addThemeButtonSize: Dp
    abstract val settingsBoxHeight: Dp
    abstract val themeItemPadding: PaddingValues

    object Compact : Values() {
        override val mainMenuTitleSize = 80.sp
        override val buttonTextSize = 24.sp
        override val mainMenuIconSize = 36.dp
        override val mainMenuPadding = PaddingValues(horizontal = 50.dp)
        override val mainMenuHeight = 180.dp
        override val buttonHeight = 46.dp
        override val mainMenuTitlePadding = PaddingValues(vertical = 100.dp)
        override val defaultTitleFontSize = 48.sp
        override val boxCornerRadius = 20.dp
        override val statsPadding = PaddingValues(32.dp)
        override val contentFontSize = 16.sp
        override val lineWidth = 1.dp
        override val statsTableHeight = 100.dp
        override val gamesStatsBoxHeight = 400.dp
        override val gameTitleFontSize = 60.sp
        override val gamePadding = PaddingValues(horizontal = 32.dp, vertical = 32.dp)
        override val settingsInboxPadding = PaddingValues(10.dp)
        override val addThemeButtonSize = 50.dp
        override val settingsBoxHeight = 500.dp
        override val themeItemPadding =
            PaddingValues(vertical = 10.dp, horizontal = 15.dp)
    }

    object Medium : Values() {
        override val mainMenuTitleSize = 100.sp
        override val buttonTextSize = 30.sp
        override val mainMenuIconSize = 50.dp
        override val mainMenuPadding = PaddingValues(horizontal = 90.dp)
        override val mainMenuHeight = 240.dp
        override val buttonHeight = 56.dp
        override val mainMenuTitlePadding = PaddingValues(vertical = 140.dp)
        override val defaultTitleFontSize = 56.sp
        override val boxCornerRadius = 22.dp
        override val statsPadding = PaddingValues(48.dp)
        override val contentFontSize = 24.sp
        override val lineWidth = 2.dp
        override val statsTableHeight = 150.dp
        override val gamesStatsBoxHeight = 500.dp
        override val gameTitleFontSize = 80.sp
        override val gamePadding = PaddingValues(horizontal = 60.dp, vertical = 32.dp)
        override val settingsInboxPadding = PaddingValues(15.dp)
        override val addThemeButtonSize = 70.dp
        override val settingsBoxHeight = 600.dp
        override val themeItemPadding =
            PaddingValues(vertical = 12.dp, horizontal = 17.dp)
    }

    object Expanded : Values() {
        override val mainMenuTitleSize = 120.sp
        override val buttonTextSize = 36.sp
        override val mainMenuIconSize = 64.dp
        override val mainMenuPadding = PaddingValues(horizontal = 140.dp)
        override val mainMenuHeight = 300.dp
        override val buttonHeight = 72.dp
        override val mainMenuTitlePadding = PaddingValues(vertical = 180.dp)
        override val defaultTitleFontSize = 64.sp
        override val boxCornerRadius = 25.dp
        override val statsPadding = PaddingValues(60.dp)
        override val contentFontSize = 32.sp
        override val lineWidth = 3.dp
        override val statsTableHeight = 200.dp
        override val gamesStatsBoxHeight = 600.dp
        override val gameTitleFontSize = 60.sp
        override val gamePadding = PaddingValues(horizontal = 100.dp, vertical = 32.dp)
        override val settingsInboxPadding = PaddingValues(20.dp)
        override val addThemeButtonSize = 90.dp
        override val settingsBoxHeight = 700.dp
        override val themeItemPadding =
            PaddingValues(vertical = 15.dp, horizontal = 20.dp)
    }
}

/*

 */