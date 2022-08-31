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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vlibrovs.twentyfortyeight.R
import com.vlibrovs.twentyfortyeight.data.entity.Game
import com.vlibrovs.twentyfortyeight.data.model.Theme
import com.vlibrovs.twentyfortyeight.ui.common.composables.Button
import com.vlibrovs.twentyfortyeight.ui.common.composables.SecondaryBackgroundBox
import com.vlibrovs.twentyfortyeight.ui.common.fonts.Fonts
import com.vlibrovs.twentyfortyeight.ui.common.window.rememberWindowInfo
import java.util.*

@Preview(name = "Compact", device = Devices.PIXEL_4)
@Preview(name = "Expanded", device = Devices.PIXEL_C, heightDp = 1280, widthDp = 900)
@Composable
fun StatsScreen(
    theme: Theme = Theme.Main,
    games: List<Game> = listOf(
        Game(1, 19287, 236, Date()),
        Game(2, 187236, 123, Date()),
        Game(3, 127863, 136, Date()),
        Game(4, 123412, 743, Date())
    ),
    bestScore: Int = 0,
    averageScore: Int = 0,
    mostMoves: Int = 0,
    averageMoves: Int = 0
) {
    val windowInfo = rememberWindowInfo()
    val titleFontSize = windowInfo.rememberValues(compact = 48.sp, medium = 56.sp, expanded = 64.sp)
    val boxCornerRadius =
        windowInfo.rememberValues(compact = 20.dp, medium = 22.dp, expanded = 25.dp)
    val padding = windowInfo.rememberValues(compact = 32.dp, medium = 48.dp, expanded = 60.dp)
    val contentFontSize =
        windowInfo.rememberValues(compact = 16.sp, medium = 24.sp, expanded = 32.sp)
    val lineWidth = windowInfo.rememberValues(compact = 1.dp, medium = 2.dp, expanded = 3.dp)
    val tableHeight =
        windowInfo.rememberValues(compact = 100.dp, medium = 150.dp, expanded = 200.dp)
    val secondaryFontSize =
        windowInfo.rememberValues(compact = 24.sp, medium = 30.sp, expanded = 36.sp)
    val gameBoxHeight =
        windowInfo.rememberValues(compact = 400.dp, medium = 500.dp, expanded = 600.dp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(theme.backgroundGradient)
            )
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Title
        Text(
            text = stringResource(id = R.string.stats),
            color = theme.textColor,
            fontSize = titleFontSize,
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
                .height(tableHeight),
            theme = theme,
            cornerRadius = boxCornerRadius
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
                            .width(lineWidth)
                            .fillMaxHeight(), color = theme.linesColor
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.score),
                        color = theme.textColor,
                        fontSize = contentFontSize,
                        fontFamily = Fonts.Poppins,
                        fontWeight = FontWeight.Normal
                    )
                    Divider(
                        modifier = Modifier
                            .width(lineWidth)
                            .fillMaxHeight(), color = theme.linesColor
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.moves),
                        color = theme.textColor,
                        fontSize = contentFontSize,
                        fontFamily = Fonts.Poppins,
                        fontWeight = FontWeight.Normal
                    )
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(lineWidth)
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
                        fontSize = contentFontSize,
                        fontFamily = Fonts.Poppins,
                        fontWeight = FontWeight.Normal
                    )
                    Divider(
                        modifier = Modifier
                            .width(lineWidth)
                            .fillMaxHeight(), color = theme.linesColor
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        textAlign = TextAlign.Center,
                        text = "$bestScore",
                        color = theme.textColor,
                        fontSize = contentFontSize,
                        fontFamily = Fonts.Poppins,
                        fontWeight = FontWeight.Bold
                    )
                    Divider(
                        modifier = Modifier
                            .width(lineWidth)
                            .fillMaxHeight(), color = theme.linesColor
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "$mostMoves",
                        color = theme.textColor,
                        fontSize = contentFontSize,
                        fontFamily = Fonts.Poppins,
                        fontWeight = FontWeight.Bold
                    )
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(lineWidth)
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
                        fontSize = contentFontSize,
                        fontFamily = Fonts.Poppins,
                        fontWeight = FontWeight.Normal
                    )
                    Divider(
                        modifier = Modifier
                            .width(lineWidth)
                            .fillMaxHeight(), color = theme.linesColor
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        textAlign = TextAlign.Center,
                        text = "$averageScore",
                        color = theme.textColor,
                        fontSize = contentFontSize,
                        fontFamily = Fonts.Poppins,
                        fontWeight = FontWeight.Bold
                    )
                    Divider(
                        modifier = Modifier
                            .width(lineWidth)
                            .fillMaxHeight(), color = theme.linesColor
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "$averageMoves",
                        color = theme.textColor,
                        fontSize = contentFontSize,
                        fontFamily = Fonts.Poppins,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Games
        SecondaryBackgroundBox(
            modifier = Modifier
                .fillMaxWidth().height(gameBoxHeight),
            theme = theme,
            cornerRadius = boxCornerRadius
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
                    fontSize = secondaryFontSize,
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
                        fontSize = contentFontSize,
                        fontFamily = Fonts.Poppins,
                        fontWeight = FontWeight.Normal,
                        color = theme.textColor
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.score),
                        fontSize = contentFontSize,
                        fontFamily = Fonts.Poppins,
                        fontWeight = FontWeight.Normal,
                        color = theme.textColor
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(0.4f),
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.moves),
                        fontSize = contentFontSize,
                        fontFamily = Fonts.Poppins,
                        fontWeight = FontWeight.Normal,
                        color = theme.textColor
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.finished),
                        fontSize = contentFontSize,
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
                                fontSize = contentFontSize,
                                fontFamily = Fonts.Poppins,
                                fontWeight = FontWeight.SemiBold,
                                color = theme.textColor
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth(0.5f),
                                textAlign = TextAlign.Center,
                                text = "${game.score}",
                                fontSize = contentFontSize,
                                fontFamily = Fonts.Poppins,
                                fontWeight = FontWeight.SemiBold,
                                color = theme.textColor
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth(0.4f),
                                textAlign = TextAlign.Center,
                                text = "${game.moves}",
                                fontSize = contentFontSize,
                                fontFamily = Fonts.Poppins,
                                fontWeight = FontWeight.SemiBold,
                                color = theme.textColor
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                text = game.date,
                                fontSize = contentFontSize,
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
            fontSize = secondaryFontSize,
            onClick = { },
            theme = theme
        )

    }
}