package com.vlibrovs.twentyfortyeight.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vlibrovs.twentyfortyeight.R
import com.vlibrovs.twentyfortyeight.data.model.Theme
import com.vlibrovs.twentyfortyeight.ui.common.fonts.Fonts
import com.vlibrovs.twentyfortyeight.ui.common.window.WindowDensity
import com.vlibrovs.twentyfortyeight.ui.common.window.rememberWindowInfo

@Preview(name = "Compact", device = Devices.PIXEL_4)
@Preview(name = "Expanded", device = Devices.PIXEL_C, heightDp = 1280, widthDp = 900)
@Composable
fun GameScreen(
    theme: Theme = Theme.Main
) {
    val windowInfo = rememberWindowInfo()
    val primaryFontSize = remember {
        when (windowInfo.screenWidthInfo) {
            WindowDensity.WindowType.Compact -> 60.sp
            WindowDensity.WindowType.Medium -> 80.sp
            WindowDensity.WindowType.Expanded -> 100.sp
        }
    }
    val secondaryFontSize = remember {
        when (windowInfo.screenWidthInfo) {
            WindowDensity.WindowType.Compact -> 24.sp
            WindowDensity.WindowType.Medium -> 30.sp
            WindowDensity.WindowType.Expanded -> 36.sp
        }
    }
    val lineWidth = remember {
        when (windowInfo.screenWidthInfo) {
            WindowDensity.WindowType.Compact -> 1.dp
            WindowDensity.WindowType.Medium -> 2.dp
            WindowDensity.WindowType.Expanded -> 3.dp
        }
    }
    var score by remember {
        mutableStateOf(0)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(theme.backgroundGradient))
            .padding(
                horizontal =
                when (windowInfo.screenWidthInfo) {
                    WindowDensity.WindowType.Compact -> 32.dp
                    WindowDensity.WindowType.Medium -> 60.dp
                    WindowDensity.WindowType.Expanded -> 100.dp
                },
                vertical = 32.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "2048",
            color = theme.textColor,
            fontSize = primaryFontSize,
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
                text = stringResource(id = R.string.score),
                textAlign = TextAlign.Start,
                fontFamily = Fonts.Poppins,
                fontWeight = FontWeight.Normal,
                color = theme.textColor,
                fontSize = secondaryFontSize
            )
            Text(
                modifier = Modifier
                    .padding(end = 20.dp)
                    .fillMaxWidth(),
                text = "$score",
                textAlign = TextAlign.End,
                fontFamily = Fonts.Poppins,
                fontWeight = FontWeight.SemiBold,
                color = theme.textColor,
                fontSize = secondaryFontSize
            )
        }

        Box(
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
                        .width(lineWidth)
                        .background(color = theme.linesColor)
                )
                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(lineWidth)
                        .background(color = theme.linesColor)
                )
                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(lineWidth)
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
                        .height(lineWidth)
                        .background(color = theme.linesColor)
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(lineWidth)
                        .background(color = theme.linesColor)
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(lineWidth)
                        .background(color = theme.linesColor)
                )
            }
            GameLayer(modifier = Modifier.fillMaxSize())
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = theme.buttonColor,
                    shape = RoundedCornerShape(50.dp)
                )
                .clickable {

                }
        ) {
            Text(
                modifier = Modifier
                    .padding(end = 20.dp)
                    .fillMaxWidth(),
                text = stringResource(id = R.string.back),
                textAlign = TextAlign.Center,
                fontFamily = Fonts.Poppins,
                fontWeight = FontWeight.SemiBold,
                color = theme.textColor,
                fontSize = secondaryFontSize
            )
        }
    }
}

@Composable
fun GameLayer(
    modifier: Modifier = Modifier
) {

}