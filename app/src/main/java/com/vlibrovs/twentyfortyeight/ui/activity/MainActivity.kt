package com.vlibrovs.twentyfortyeight.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.vlibrovs.twentyfortyeight.common.Constants
import com.vlibrovs.twentyfortyeight.data.model.theme.DefaultThemes
import com.vlibrovs.twentyfortyeight.data.model.theme.Theme
import com.vlibrovs.twentyfortyeight.ui.common.navigation.Screen
import com.vlibrovs.twentyfortyeight.ui.screens.*
import com.vlibrovs.twentyfortyeight.ui.screens.gamescreen.GameScreen
import com.vlibrovs.twentyfortyeight.ui.viewmodel.EditViewModel
import com.vlibrovs.twentyfortyeight.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.ParametersHolder

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<MainViewModel>()
    private val editViewModel by viewModel<EditViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences(Constants.PREFERENCE_KEY, MODE_PRIVATE)
        editViewModel.onThemeSave = {
            viewModel.apply {
                getThemes()
            }
        }
        viewModel.sharedPreferences = sharedPreferences
        viewModel.selectThemeById(
            sharedPreferences.getString(Constants.SELECTED_THEME_ID, "-1")?.toInt()
                ?: -1
        )
        setContent {
            val systemUiController = rememberSystemUiController()
            val navController = rememberNavController()
            systemUiController.setStatusBarColor(viewModel.run {
                getThemeById(selectedThemeId.value)!!
            }.backgroundGradient.colorStart)
            NavHost(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                startDestination = Screen.MainMenu.route
            ) {
                composable(route = Screen.MainMenu.route) {
                    MainMenuScreen(
                        theme = viewModel.run {
                            getThemeById(selectedThemeId.value)!!
                        },
                        navController = navController,
                        viewModel = viewModel
                    )
                }

                composable(route = Screen.Game.route + "/{newGame}", arguments = listOf(
                    navArgument(name = "newGame") {
                        type = NavType.BoolType
                        nullable = false
                        defaultValue = false
                    }
                )) {
                    GameScreen(
                        theme = viewModel.run {
                            getThemeById(selectedThemeId.value)!!
                        },
                        navController = navController,
                        viewModel = viewModel,
                        newGame = it.arguments?.getBoolean("newGame") ?: false
                    )
                }

                composable(route = Screen.Settings.route) {
                    SettingsScreen(
                        theme = viewModel.run {
                            getThemeById(selectedThemeId.value)!!
                        },
                        navController = navController,
                        themes = viewModel.themeList,
                        viewModel = viewModel,
                        editViewModel = editViewModel
                    )
                }

                composable(route = Screen.Stats.route) {
                    StatsScreen(
                        theme = viewModel.run {
                            getThemeById(selectedThemeId.value)!!
                        }, navController = navController,
                        games = viewModel.gameList,
                        bestScore = viewModel.bestScore,
                        averageMoves = viewModel.averageMoves,
                        averageScore = viewModel.averageScore,
                        mostMoves = viewModel.mostMoves
                    )
                }

                composable(route = Screen.ThemeEdit.route) {
                    ThemeEditScreen(
                        currentTheme = viewModel.run {
                            getThemeById(selectedThemeId.value) ?: DefaultThemes.Main
                        },
                        navController = navController,
                        editViewModel = editViewModel
                    )
                }

                composable(route = Screen.TilesStyles.route) {
                    TileStylesScreen(
                        theme = viewModel.run {
                            getThemeById(selectedThemeId.value)!!
                        },
                        editViewModel = editViewModel,
                        navController = navController
                    )
                }

                composable(route = Screen.ColorPicker.route) {
                    ColorPickerScreen(
                        theme = viewModel.run {
                            getThemeById(selectedThemeId.value)!!
                        },
                        navController = navController,
                        editViewModel = editViewModel
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.gameSaver()
    }
}