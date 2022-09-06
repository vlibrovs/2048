package com.vlibrovs.twentyfortyeight.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.vlibrovs.twentyfortyeight.data.entity.Game
import com.vlibrovs.twentyfortyeight.data.model.Theme
import com.vlibrovs.twentyfortyeight.ui.common.navigation.Screen
import com.vlibrovs.twentyfortyeight.ui.screens.*
import com.vlibrovs.twentyfortyeight.ui.screens.gamescreen.GameScreen
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            var theme by remember {
                mutableStateOf(Theme.Main)
            }
            val navController = rememberNavController()
            systemUiController.setStatusBarColor(theme.backgroundGradient.colorStart)
            NavHost(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                startDestination = Screen.MainMenu.route
            ) {
                composable(route = Screen.MainMenu.route) {
                    MainMenuScreen(theme = theme, navController = navController)
                }

                composable(route = Screen.Game.route) {
                    GameScreen(theme = theme, navController = navController)
                }

                composable(route = Screen.Settings.route) {
                    SettingsScreen(
                        theme = theme,
                        navController = navController,
                        themes = listOf(theme)
                    ) // TODO: Replace with actual list of themes
                }

                composable(route = Screen.Stats.route) {
                    StatsScreen(
                        theme = theme, navController = navController,
                        games = listOf(
                            Game(1, 19287, 236, Date()),
                            Game(2, 187236, 123, Date()),
                            Game(3, 127863, 136, Date()),
                            Game(4, 123412, 743, Date())
                        ),
                        bestScore = 0,
                        averageMoves = 0,
                        averageScore = 0,
                        mostMoves = 0
                    ) // TODO: Replace with actual data
                }

                composable(route = Screen.ThemeEdit.route + "?editThemeName={editThemeName}",
                    arguments = listOf(
                        navArgument("editThemeName") {
                            type = NavType.StringType
                            nullable = true
                            defaultValue = null
                        }
                    )) {
                    ThemeEditScreen(
                        currentTheme = theme,
                        editTheme = theme,
                        navController = navController
                    ) // TODO: Replace with actual argument
                }

                composable(route = Screen.TilesStyles.route + "?editThemeName={editThemeName}",
                    arguments = listOf(
                        navArgument("editThemeName") {
                            type = NavType.StringType
                            nullable = true
                            defaultValue = null
                        }
                    )) {
                    TileStylesScreen(
                        theme = theme,
                        editTheme = theme,
                        navController = navController
                    ) // TODO: Replace with actual argument
                }

                composable(route = Screen.ColorPicker.route + "/{editColor}/{entryRoute}", arguments = listOf(
                    navArgument("editColor") {
                        type = NavType.IntType
                        nullable = false
                        defaultValue = 0xffffffff
                    },
                    navArgument("entryRoute") {
                        type = NavType.StringType
                        nullable = false
                        defaultValue = ""
                    }
                )) { entry ->
                    ColorPickerScreen(
                        theme = theme,
                        color = if (entry.arguments != null) Color(entry.arguments!!.getInt("editColor")) else Color.White,
                        navController = navController,
                        entryRoute = entry.arguments!!.getString("entryRoute")!!
                    )
                }
            }
        }
    }
}