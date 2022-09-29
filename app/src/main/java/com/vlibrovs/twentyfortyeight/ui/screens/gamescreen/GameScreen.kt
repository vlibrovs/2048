package com.vlibrovs.twentyfortyeight.ui.screens.gamescreen

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vlibrovs.twentyfortyeight.R
import com.vlibrovs.twentyfortyeight.common.Constants
import com.vlibrovs.twentyfortyeight.common.getValues
import com.vlibrovs.twentyfortyeight.data.model.game.Game
import com.vlibrovs.twentyfortyeight.data.model.game.UnfinishedGame
import com.vlibrovs.twentyfortyeight.data.model.game_result.GameResult
import com.vlibrovs.twentyfortyeight.data.model.theme.Theme
import com.vlibrovs.twentyfortyeight.ui.common.composables.Button
import com.vlibrovs.twentyfortyeight.ui.common.fonts.Fonts
import com.vlibrovs.twentyfortyeight.ui.common.navigation.Screen
import com.vlibrovs.twentyfortyeight.ui.common.window.rememberWindowInfo
import com.vlibrovs.twentyfortyeight.ui.screens.Overlay
import com.vlibrovs.twentyfortyeight.ui.viewmodel.MainViewModel

@Composable
fun GameScreen(
    theme: Theme,
    navController: NavController,
    viewModel: MainViewModel,
    newGame: Boolean,
) {
    val values = getValues(rememberWindowInfo().screenWidthInfo)
    val gameResult by remember {
        viewModel.gameResult
    }
    var game by remember {
        mutableStateOf(
            if (newGame) {
                UnfinishedGame()
            } else
                viewModel.getCurrentGame()!!.toSizeFourUnfinishedGame()
        )
    }
    val scoreState = remember {
        mutableStateOf(game.score)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(theme.backgroundGradient.toList()))
                .padding(values.gamePadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "2048",
                color = theme.textColor,
                fontSize = values.gameTitleFontSize,
                fontFamily = Fonts.Poppins,
                fontWeight = FontWeight.SemiBold,
                style = TextStyle(
                    shadow = Shadow(
                        Color(0x30000000),
                        blurRadius = 4f,
                        offset = Offset(0f, 6f)
                    )
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = theme.secondaryBackgroundColor,
                        shape = RoundedCornerShape(50.dp)
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(start = 20.dp),
                    text = stringResource(id = R.string.score) + ':',
                    textAlign = TextAlign.Start,
                    fontFamily = Fonts.Poppins,
                    fontWeight = FontWeight.Normal,
                    color = theme.textColor,
                    fontSize = values.buttonTextSize
                )
                Text(
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .fillMaxWidth(),
                    text = "${scoreState.value}",
                    textAlign = TextAlign.End,
                    fontFamily = Fonts.Poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = theme.textColor,
                    fontSize = values.buttonTextSize
                )
            }

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(
                        color = theme.secondaryBackgroundColor,
                        shape = RoundedCornerShape(20.dp)
                    )
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Divider(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(values.lineWidth)
                            .background(color = theme.linesColor)
                    )
                    Divider(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(values.lineWidth)
                            .background(color = theme.linesColor)
                    )
                    Divider(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(values.lineWidth)
                            .background(color = theme.linesColor)
                    )
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(values.lineWidth)
                            .background(color = theme.linesColor)
                    )
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(values.lineWidth)
                            .background(color = theme.linesColor)
                    )
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(values.lineWidth)
                            .background(color = theme.linesColor)
                    )
                }
                GameLayer(
                    modifier = Modifier.fillMaxSize(),
                    innerPadding = values.gameFieldInnerPadding,
                    theme = theme,
                    squareSize = maxWidth / 4,
                    scoreState = scoreState,
                    viewModel = viewModel,
                    game = game
                )
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.back),
                fontSize = values.buttonTextSize,
                onClick = {
                    viewModel.saveGame(game)
                    navController.navigate(Screen.MainMenu.route)
                },
                theme = theme
            )
        }
        AnimatedVisibility(
            gameResult != GameResult.EMPTY,
            enter = fadeIn(tween(durationMillis = 500)),
            exit = fadeOut(tween(durationMillis = 500))
        ) {
            Overlay(
                modifier = Modifier.fillMaxSize(),
                theme = theme,
                text = when (gameResult) {
                    GameResult.EMPTY -> ""
                    GameResult.WIN -> stringResource(id = R.string.game_win_text)
                    GameResult.LOSS -> stringResource(id = R.string.game_loss_text)
                },
                onButton1Click = {
                    when (gameResult) {
                        GameResult.EMPTY -> Unit
                        GameResult.WIN -> {
                            viewModel.gameResult.value = GameResult.EMPTY
                        }
                        GameResult.LOSS -> {
                            viewModel.gameResult.value = GameResult.EMPTY
                            viewModel.finishCurrentGame()
                            navController.navigate(Screen.Game.route+"/true")
                        }
                    }
                },
                button1Text = when (gameResult) {
                    GameResult.EMPTY -> ""
                    GameResult.WIN -> stringResource(id = R.string.continueStr)
                    GameResult.LOSS -> stringResource(id = R.string.new_game)
                },
                onButton2Click = {
                    when (gameResult) {
                        GameResult.EMPTY -> Unit
                        GameResult.WIN -> {
                            viewModel.gameResult.value = GameResult.EMPTY
                            viewModel.finishCurrentGame()
                            navController.navigate(Screen.Game.route+"/true")
                        }
                        GameResult.LOSS -> {
                            viewModel.gameResult.value = GameResult.EMPTY
                            viewModel.finishCurrentGame()
                            navController.navigate(Screen.MainMenu.route)
                        }
                    }
                },
                button2Text = when (gameResult) {
                    GameResult.EMPTY -> ""
                    GameResult.WIN -> stringResource(id = R.string.new_game)
                    GameResult.LOSS -> stringResource(id = R.string.back_to_menu)
                }
            )

        }
    }
    BackHandler {
        if (gameResult == GameResult.EMPTY) {
            navController.navigate(Screen.MainMenu.route)
            viewModel.saveGame(game)
        }
    }
}