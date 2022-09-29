package com.vlibrovs.twentyfortyeight.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.vlibrovs.twentyfortyeight.R
import com.vlibrovs.twentyfortyeight.common.getValues
import com.vlibrovs.twentyfortyeight.data.model.game_result.GameResult
import com.vlibrovs.twentyfortyeight.data.model.theme.Theme
import com.vlibrovs.twentyfortyeight.ui.common.composables.Button
import com.vlibrovs.twentyfortyeight.ui.common.composables.IconButton
import com.vlibrovs.twentyfortyeight.ui.common.fonts.Fonts
import com.vlibrovs.twentyfortyeight.ui.common.navigation.Screen
import com.vlibrovs.twentyfortyeight.ui.common.window.rememberWindowInfo
import com.vlibrovs.twentyfortyeight.ui.viewmodel.MainViewModel

@Composable
fun MainMenuScreen(
    theme: Theme,
    navController: NavController,
    viewModel: MainViewModel
) {
    val values = getValues(rememberWindowInfo().screenWidthInfo)
    var overlayVisibility by remember {
        mutableStateOf(false)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(theme.backgroundGradient.toList()))
                .padding(values.mainMenuPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(values.mainMenuTitlePadding),
                text = "2048",
                color = theme.textColor,
                fontSize = values.mainMenuTitleSize,
                fontFamily = Fonts.Poppins,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    shadow = Shadow(
                        Color(0x30000000),
                        blurRadius = 4f,
                        offset = Offset(0f, 6f)
                    )
                )
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(values.mainMenuHeight),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(values.buttonHeight),
                    text = stringResource(id = R.string.continueStr),
                    fontSize = values.buttonTextSize,
                    onClick = {
                        navController.navigate(Screen.Game.route + "/${viewModel.getCurrentGame() == null}")
                    },
                    theme = theme
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(values.buttonHeight),
                    text = stringResource(id = R.string.new_game),
                    fontSize = values.buttonTextSize,
                    onClick = {
                        val currentGame = viewModel.getCurrentGame()
                        if (currentGame != null) {
                            overlayVisibility = true
                        } else navController.navigate(Screen.Game.route + "/true")
                    },
                    theme = theme
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(values.buttonHeight),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        modifier = Modifier
                            .fillMaxWidth(0.48f)
                            .fillMaxHeight(),
                        icon = painterResource(id = R.drawable.ic_settings),
                        contentDescription = stringResource(id = R.string.settings),
                        iconSize = values.mainMenuIconSize,
                        onClick = { navController.navigate(Screen.Settings.route) },
                        theme = theme
                    )
                    IconButton(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .fillMaxHeight(),
                        icon = painterResource(id = R.drawable.ic_bar_chart),
                        contentDescription = stringResource(id = R.string.stats),
                        iconSize = values.mainMenuIconSize,
                        onClick = { navController.navigate(Screen.Stats.route) },
                        theme = theme
                    )
                }
            }
        }
        AnimatedVisibility(
            visible = overlayVisibility,
            enter = fadeIn(tween(durationMillis = 500)),
            exit = fadeOut(tween(durationMillis = 500))
        ) {
            Overlay(
                modifier = Modifier.fillMaxSize(),
                theme = theme,
                text = "Your current progress will be lost.\nAre you sure?",
                onButton1Click = {
                    overlayVisibility = false
                    viewModel.finishCurrentGame()
                    navController.navigate(Screen.Game.route + "/true")
                },
                button1Text = "New Game",
                onButton2Click = {
                    overlayVisibility = false
                },
                button2Text = "Cancel"
            )
        }
    }
}