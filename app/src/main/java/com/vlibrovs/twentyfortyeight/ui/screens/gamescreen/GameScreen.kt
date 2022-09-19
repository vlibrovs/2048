package com.vlibrovs.twentyfortyeight.ui.screens.gamescreen

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
import com.vlibrovs.twentyfortyeight.common.getValues
import com.vlibrovs.twentyfortyeight.data.model.theme.Theme
import com.vlibrovs.twentyfortyeight.ui.common.composables.Button
import com.vlibrovs.twentyfortyeight.ui.common.fonts.Fonts
import com.vlibrovs.twentyfortyeight.ui.common.navigation.Screen
import com.vlibrovs.twentyfortyeight.ui.common.window.rememberWindowInfo

@Composable
fun GameScreen(
    theme: Theme,
    navController: NavController
) {
    val values = getValues(rememberWindowInfo().screenWidthInfo)
    val scoreState = remember {
        mutableStateOf(0)
    }
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
                scoreState = scoreState
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.back),
            fontSize = values.buttonTextSize,
            onClick = { navController.navigate(Screen.MainMenu.route) },
            theme = theme
        )
    }
}