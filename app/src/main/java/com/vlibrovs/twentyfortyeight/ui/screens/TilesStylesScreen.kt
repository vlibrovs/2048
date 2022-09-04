package com.vlibrovs.twentyfortyeight.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.vlibrovs.twentyfortyeight.R
import com.vlibrovs.twentyfortyeight.common.getValues
import com.vlibrovs.twentyfortyeight.data.model.Theme
import com.vlibrovs.twentyfortyeight.ui.common.composables.Button
import com.vlibrovs.twentyfortyeight.ui.common.composables.ColorCircle
import com.vlibrovs.twentyfortyeight.ui.common.composables.SecondaryBackgroundBox
import com.vlibrovs.twentyfortyeight.ui.common.composables.Tile
import com.vlibrovs.twentyfortyeight.ui.common.window.rememberWindowInfo

@Preview(name = "Compact", device = Devices.PIXEL_4)
@Preview(name = "Expanded", device = Devices.PIXEL_C, heightDp = 1280, widthDp = 900)
@Composable
fun TileStylesScreen(
    theme: Theme = Theme.Main
) {
    val values = getValues(rememberWindowInfo().screenWidthInfo)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(theme.backgroundGradient)
            )
            .padding(values.statsPadding),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SecondaryBackgroundBox(
            modifier = Modifier.fillMaxHeight(0.9f),
            theme = theme,
            cornerRadius = values.boxCornerRadius
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(values.settingsInboxPadding)
                    .fillMaxSize()
            ) {
                items(17) { index ->
                    TileStyleSetting(tileLevel = index + 1, theme = theme)
                }
            }
        }
        Button(
            text = stringResource(id = R.string.save),
            fontSize = values.buttonTextSize,
            onClick = { },
            theme = theme
        )
    }
}

@Composable
fun TileStyleSetting(
    tileLevel: Int,
    theme: Theme
) {
    val values = getValues(rememberWindowInfo().screenWidthInfo)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = values.settingsInboxPadding.calculateTopPadding()),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Tile(
            level = tileLevel,
            theme = theme,
            size = values.tileStyleSize,
            cornerRadius = values.tileStyleCornerRadius,
            fontMap = values.tileStyleFontSize
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            ColorCircle(
                fillColor = theme.tileStyles[tileLevel - 1].colorStart,
                outlineColor = theme.linesColor,
                outlineWidth = values.colorCircleOutlineWidth,
                size = values.addThemeButtonSize
            )
            Spacer(modifier = Modifier.width(values.settingsInboxPadding.calculateTopPadding()))
            ColorCircle(
                fillColor = theme.tileStyles[tileLevel - 1].colorEnd,
                outlineColor = theme.linesColor,
                outlineWidth = values.colorCircleOutlineWidth,
                size = values.addThemeButtonSize
            )
        }
    }
}