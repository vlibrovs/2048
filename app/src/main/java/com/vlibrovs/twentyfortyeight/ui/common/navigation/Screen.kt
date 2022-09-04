package com.vlibrovs.twentyfortyeight.ui.common.navigation

sealed class Screen(val route: String) {
    object ColorPicker : Screen("ColorPicker")
    object Game : Screen("Game")
    object MainMenu : Screen("MainMenu")
    object Settings : Screen("Settings")
    object Stats : Screen("Stats")
    object ThemeEdit : Screen("ThemeEdit")
    object TilesStyles : Screen("TilesStyles")
}