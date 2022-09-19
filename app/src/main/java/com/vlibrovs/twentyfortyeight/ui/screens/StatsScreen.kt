package com.vlibrovs.twentyfortyeight.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.vlibrovs.twentyfortyeight.common.getValues
import com.vlibrovs.twentyfortyeight.data.model.game.Game
import com.vlibrovs.twentyfortyeight.data.model.theme.Theme
import com.vlibrovs.twentyfortyeight.ui.common.composables.Button
import com.vlibrovs.twentyfortyeight.ui.common.composables.SecondaryBackgroundBox
import com.vlibrovs.twentyfortyeight.ui.common.fonts.Fonts
import com.vlibrovs.twentyfortyeight.ui.common.navigation.Screen
import com.vlibrovs.twentyfortyeight.ui.common.window.rememberWindowInfo

@Composable
fun StatsScreen(
    theme: Theme,
    games: List<Game>,
    bestScore: Int,
    averageScore: Int,
    mostMoves: Int,
    averageMoves: Int,
    navController: NavController
) {
    val values = getValues(rememberWindowInfo().screenWidthInfo)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(theme.backgroundGradient.toList())
            )
            .padding(values.statsPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Title
        Text(
            text = stringResource(id = R.string.stats),
            color = theme.textColor,
            fontSize = values.defaultTitleFontSize,
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
        // Table
        SecondaryBackgroundBox(
            modifier = Modifier
                .fillMaxWidth()
                .height(values.statsTableHeight),
            theme = theme,
            cornerRadius = values.boxCornerRadius
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.33f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.fillMaxWidth(0.33f))
                    Divider(
                        modifier = Modifier
                            .width(values.lineWidth)
                            .fillMaxHeight(), color = theme.linesColor
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.score),
                        color = theme.textColor,
                        fontSize = values.contentFontSize,
                        fontFamily = Fonts.Poppins,
                        fontWeight = FontWeight.Normal
                    )
                    Divider(
                        modifier = Modifier
                            .width(values.lineWidth)
                            .fillMaxHeight(), color = theme.linesColor
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.moves),
                        color = theme.textColor,
                        fontSize = values.contentFontSize,
                        fontFamily = Fonts.Poppins,
                        fontWeight = FontWeight.Normal
                    )
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(values.lineWidth)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(0.33f),
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.best),
                        color = theme.textColor,
                        fontSize = values.contentFontSize,
                        fontFamily = Fonts.Poppins,
                        fontWeight = FontWeight.Normal
                    )
                    Divider(
                        modifier = Modifier
                            .width(values.lineWidth)
                            .fillMaxHeight(), color = theme.linesColor
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        textAlign = TextAlign.Center,
                        text = "$bestScore",
                        color = theme.textColor,
                        fontSize = values.contentFontSize,
                        fontFamily = Fonts.Poppins,
                        fontWeight = FontWeight.Bold
                    )
                    Divider(
                        modifier = Modifier
                            .width(values.lineWidth)
                            .fillMaxHeight(), color = theme.linesColor
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "$mostMoves",
                        color = theme.textColor,
                        fontSize = values.contentFontSize,
                        fontFamily = Fonts.Poppins,
                        fontWeight = FontWeight.Bold
                    )
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(values.lineWidth)
                )
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(0.33f),
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.average),
                        color = theme.textColor,
                        fontSize = values.contentFontSize,
                        fontFamily = Fonts.Poppins,
                        fontWeight = FontWeight.Normal
                    )
                    Divider(
                        modifier = Modifier
                            .width(values.lineWidth)
                            .fillMaxHeight(), color = theme.linesColor
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        textAlign = TextAlign.Center,
                        text = "$averageScore",
                        color = theme.textColor,
                        fontSize = values.contentFontSize,
                        fontFamily = Fonts.Poppins,
                        fontWeight = FontWeight.Bold
                    )
                    Divider(
                        modifier = Modifier
                            .width(values.lineWidth)
                            .fillMaxHeight(), color = theme.linesColor
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "$averageMoves",
                        color = theme.textColor,
                        fontSize = values.contentFontSize,
                        fontFamily = Fonts.Poppins,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Games
        SecondaryBackgroundBox(
            modifier = Modifier
                .fillMaxWidth().height(values.gamesStatsBoxHeight),
            theme = theme,
            cornerRadius = values.boxCornerRadius
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.games),
                    color = theme.textColor,
                    fontSize = values.buttonTextSize,
                    fontFamily = Fonts.Poppins,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(0.15f),
                        textAlign = TextAlign.Center,
                        text = "№",
                        fontSize = values.contentFontSize,
                        fontFamily = Fonts.Poppins,
                        fontWeight = FontWeight.Normal,
                        color = theme.textColor
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.score),
                        fontSize = values.contentFontSize,
                        fontFamily = Fonts.Poppins,
                        fontWeight = FontWeight.Normal,
                        color = theme.textColor
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(0.4f),
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.moves),
                        fontSize = values.contentFontSize,
                        fontFamily = Fonts.Poppins,
                        fontWeight = FontWeight.Normal,
                        color = theme.textColor
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.finished),
                        fontSize = values.contentFontSize,
                        fontFamily = Fonts.Poppins,
                        fontWeight = FontWeight.Normal,
                        color = theme.textColor
                    )
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(games.reversed()) { game ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(0.15f),
                                textAlign = TextAlign.Center,
                                text = "${game.number}",
                                fontSize = values.contentFontSize,
                                fontFamily = Fonts.Poppins,
                                fontWeight = FontWeight.SemiBold,
                                color = theme.textColor
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth(0.5f),
                                textAlign = TextAlign.Center,
                                text = "${game.score}",
                                fontSize = values.contentFontSize,
                                fontFamily = Fonts.Poppins,
                                fontWeight = FontWeight.SemiBold,
                                color = theme.textColor
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth(0.4f),
                                textAlign = TextAlign.Center,
                                text = "${game.moves}",
                                fontSize = values.contentFontSize,
                                fontFamily = Fonts.Poppins,
                                fontWeight = FontWeight.SemiBold,
                                color = theme.textColor
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                text = if (game.finished) game.extra else "—",
                                fontSize = values.contentFontSize,
                                fontFamily = Fonts.Poppins,
                                fontWeight = FontWeight.SemiBold,
                                color = theme.textColor
                            )
                        }
                    }
                }
            }
        }
        // Button
        Button(
            text = stringResource(id = R.string.back),
            fontSize = values.buttonTextSize,
            onClick = { navController.navigate(Screen.MainMenu.route) },
            theme = theme
        )

    }
}