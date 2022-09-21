package com.vlibrovs.twentyfortyeight.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.vlibrovs.twentyfortyeight.common.Constants
import com.vlibrovs.twentyfortyeight.ui.common.navigation.Screen
import com.vlibrovs.twentyfortyeight.ui.screens.*
import com.vlibrovs.twentyfortyeight.ui.screens.gamescreen.GameScreen
import com.vlibrovs.twentyfortyeight.ui.viewmodel.EditViewModel
import com.vlibrovs.twentyfortyeight.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<MainViewModel>()
    private val editViewModel by viewModel<EditViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences(Constants.PREFERENCE_KEY, MODE_PRIVATE)
        viewModel.sharedPreferences = sharedPreferences
        viewModel.selectTheme(
            sharedPreferences.getString(Constants.SELECTED_THEME, Constants.MAIN_THEME_NAME)
                ?: Constants.MAIN_THEME_NAME
        )
        setContent {
            val systemUiController = rememberSystemUiController()
            val theme by remember {
                mutableStateOf(viewModel.selectedTheme)
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
                        viewModel = viewModel,
                        editViewModel = editViewModel
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

                composable(route = Screen.ThemeEdit.route) {
                    ThemeEditScreen(
                        currentTheme = theme,
                        navController = navController,
                        editViewModel = editViewModel
                    )
                }

                composable(route = Screen.TilesStyles.route) {
                    TileStylesScreen(
                        theme = theme,
                        editViewModel = editViewModel,
                        navController = navController
                    )
                }

                composable(route = Screen.ColorPicker.route) {
                    ColorPickerScreen(
                        theme = theme,
                        navController = navController,
                        editViewModel = editViewModel
                    )
                }
            }
        }
    }
}