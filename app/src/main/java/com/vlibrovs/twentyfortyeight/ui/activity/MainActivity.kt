package com.vlibrovs.twentyfortyeight.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.vlibrovs.twentyfortyeight.common.Constants
import com.vlibrovs.twentyfortyeight.data.model.theme.Theme
import com.vlibrovs.twentyfortyeight.ui.common.navigation.Screen
import com.vlibrovs.twentyfortyeight.ui.screens.*
import com.vlibrovs.twentyfortyeight.ui.screens.gamescreen.GameScreen
import com.vlibrovs.twentyfortyeight.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

const val TAG = "Themes"

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences(Constants.PREFERENCE_KEY, MODE_PRIVATE)
        viewModel.sharedPreferences = sharedPreferences
        viewModel.selectTheme(
            sharedPreferences.getString(Constants.SELECTED_THEME, "Main Theme") ?: Theme.Main.name
        )
        setContent {
            val systemUiController = rememberSystemUiController()
            val theme by remember {
                viewModel.selectedTheme
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
                        themes = viewModel.themeList,
                        viewModel = viewModel
                    )
                }

                composable(route = Screen.Stats.route) {
                    StatsScreen(
                        theme = theme, navController = navController,
                        games = viewModel.gameList,
                        bestScore = viewModel.bestScore,
                        averageMoves = viewModel.averageMoves,
                        averageScore = viewModel.averageScore,
                        mostMoves = viewModel.mostMoves
                    )
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

                composable(route = Screen.ColorPicker.route + "/{editColor}/{entryRoute}",
                    arguments = listOf(
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