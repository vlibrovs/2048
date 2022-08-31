package com.vlibrovs.twentyfortyeight.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vlibrovs.twentyfortyeight.R
import com.vlibrovs.twentyfortyeight.data.model.Theme
import com.vlibrovs.twentyfortyeight.ui.common.composables.Button
import com.vlibrovs.twentyfortyeight.ui.common.composables.IconButton
import com.vlibrovs.twentyfortyeight.ui.common.fonts.Fonts
import com.vlibrovs.twentyfortyeight.ui.common.window.rememberWindowInfo

@Preview(name = "Compact", device = Devices.PIXEL_4)
@Preview(name = "Expanded", device = Devices.PIXEL_C, heightDp = 1280, widthDp = 900)
@Composable
fun MainMenuScreen(
    theme: Theme = Theme.Main
) {
    val windowInfo = rememberWindowInfo()
    val primaryFontSize =
        windowInfo.rememberValues(compact = 80.sp, medium = 100.sp, expanded = 120.sp)
    val secondaryFontSize =
        windowInfo.rememberValues(compact = 24.sp, medium = 30.sp, expanded = 36.sp)
    val iconSize =
        windowInfo.rememberValues(compact = 36.dp, medium = 50.dp, expanded = 64.dp)
    val paddingHorizontal =
        windowInfo.rememberValues(compact = 50.dp, medium = 90.dp, expanded = 140.dp)
    val menuHeight = windowInfo.rememberValues(compact = 180.dp, medium = 240.dp, expanded = 300.dp)
    val buttonHeight = windowInfo.rememberValues(compact = 46.dp, medium = 56.dp, expanded = 72.dp)
    val titlePadding = windowInfo.rememberValues(compact = 100.dp, medium = 140.dp, expanded = 180.dp)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(theme.backgroundGradient))
            .padding(horizontal = paddingHorizontal),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(vertical = titlePadding),
            text = "2048",
            color = theme.textColor,
            fontSize = primaryFontSize,
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
                .height(menuHeight),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(buttonHeight),
                text = stringResource(id = R.string.continueStr),
                fontSize = secondaryFontSize,
                onClick = {  },
                theme = theme
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(buttonHeight),
                text = stringResource(id = R.string.new_game),
                fontSize = secondaryFontSize,
                onClick = { },
                theme = theme
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(buttonHeight),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier
                        .fillMaxWidth(0.48f)
                        .fillMaxHeight(),
                    icon = painterResource(id = R.drawable.ic_settings),
                    contentDescription = stringResource(id = R.string.settings),
                    iconSize = iconSize,
                    onClick = { },
                    theme = theme
                )
                IconButton(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(),
                    icon = painterResource(id = R.drawable.ic_bar_chart),
                    contentDescription = stringResource(id = R.string.stats),
                    iconSize = iconSize,
                    onClick = { },
                    theme = theme
                )
            }
        }
    }
}