package com.vlibrovs.twentyfortyeight.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.vlibrovs.twentyfortyeight.R
import com.vlibrovs.twentyfortyeight.common.getValues
import com.vlibrovs.twentyfortyeight.data.model.theme.ColorPosition
import com.vlibrovs.twentyfortyeight.data.model.theme.Theme
import com.vlibrovs.twentyfortyeight.data.model.theme.ThemePropertyType
import com.vlibrovs.twentyfortyeight.ui.common.composables.Button
import com.vlibrovs.twentyfortyeight.ui.common.composables.ColorCircle
import com.vlibrovs.twentyfortyeight.ui.common.composables.SecondaryBackgroundBox
import com.vlibrovs.twentyfortyeight.ui.common.composables.Tile
import com.vlibrovs.twentyfortyeight.ui.common.navigation.Screen
import com.vlibrovs.twentyfortyeight.ui.common.window.rememberWindowInfo
import com.vlibrovs.twentyfortyeight.ui.viewmodel.EditViewModel

@Composable
fun TileStylesScreen(
    theme: Theme,
    editViewModel: EditViewModel,
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
                    TileStyleSetting(
                        tileLevel = index + 1,
                        editViewModel = editViewModel,
                        navController = navController
                    )
                }
            }
        }
        Button(
            text = stringResource(id = R.string.save),
            fontSize = values.buttonTextSize,
            onClick = {
                navController.navigate(Screen.ThemeEdit.route)
            },
            theme = theme
        )
    }
}

@Composable
fun TileStyleSetting(
    tileLevel: Int,
    editViewModel: EditViewModel,
    navController: NavController
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
            themeBuilder = editViewModel.themeBuilder!!,
            size = values.tileStyleSize,
            cornerRadius = values.tileStyleCornerRadius,
            fontMap = values.tileStyleFontSize
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            val style = editViewModel.themeBuilder!!.tileStyles[tileLevel]!!
            ColorCircle(
                fillColor = style.colorStart,
                outlineColor = editViewModel.themeBuilder!!.linesColor,
                outlineWidth = values.colorCircleOutlineWidth,
                size = values.addThemeButtonSize,
                onClick = {
                    editViewModel.themePropertyType = ThemePropertyType.TileColor(level = tileLevel, colorPosition = ColorPosition.START)
                    navController.navigate(Screen.ColorPicker.route)
                }
            )
            Spacer(modifier = Modifier.width(values.settingsInboxPadding.calculateTopPadding()))
            ColorCircle(
                fillColor = style.colorEnd,
                outlineColor = editViewModel.themeBuilder!!.linesColor,
                outlineWidth = values.colorCircleOutlineWidth,
                size = values.addThemeButtonSize,
                onClick = {
                    editViewModel.themePropertyType = ThemePropertyType.TileColor(level = tileLevel, colorPosition = ColorPosition.END)
                    navController.navigate(Screen.ColorPicker.route)
                }
            )
        }
    }
}