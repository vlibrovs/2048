package com.vlibrovs.twentyfortyeight.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.vlibrovs.twentyfortyeight.common.getValues
import com.vlibrovs.twentyfortyeight.data.model.theme.Theme
import com.vlibrovs.twentyfortyeight.ui.common.composables.Button
import com.vlibrovs.twentyfortyeight.ui.common.fonts.Fonts
import com.vlibrovs.twentyfortyeight.ui.common.window.rememberWindowInfo

@Composable
fun Overlay(
    modifier: Modifier = Modifier,
    theme: Theme,
    text: String,
    onButton1Click: () -> Unit,
    button1Text: String,
    onButton2Click: () -> Unit,
    button2Text: String
) {
    val values = getValues(rememberWindowInfo().screenWidthInfo)
    Box(
        modifier = modifier
            .background(color = theme.secondaryBackgroundColor)
            .padding(values.gamePadding),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                style = TextStyle(
                    color = theme.textColor,
                    fontFamily = Fonts.Poppins,
                    fontSize = values.buttonTextSize,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(values.buttonHeight * 2.5f),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(values.buttonHeight),
                    text = button1Text,
                    fontSize = values.buttonTextSize,
                    onClick = onButton1Click,
                    theme = theme
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(values.buttonHeight),
                    text = button2Text,
                    fontSize = values.buttonTextSize,
                    onClick = onButton2Click,
                    theme = theme
                )
            }
        }
    }

}

@Composable
fun Overlay(
    modifier: Modifier = Modifier,
    theme: Theme,
    text: String,
    buttonText: String,
    onButtonClick: () -> Unit,
) {
    val values = getValues(rememberWindowInfo().screenWidthInfo)
    Box(
        modifier = modifier
            .background(color = theme.secondaryBackgroundColor)
            .padding(values.gamePadding),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                style = TextStyle(
                    color = theme.textColor,
                    fontFamily = Fonts.Poppins,
                    fontSize = values.buttonTextSize,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(values.buttonHeight),
                text = buttonText,
                fontSize = values.buttonTextSize,
                onClick = onButtonClick,
                theme = theme
            )
        }
    }
}