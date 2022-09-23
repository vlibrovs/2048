package com.vlibrovs.twentyfortyeight.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vlibrovs.twentyfortyeight.R
import com.vlibrovs.twentyfortyeight.common.getValues
import com.vlibrovs.twentyfortyeight.data.model.theme.ColorPosition
import com.vlibrovs.twentyfortyeight.data.model.theme.Theme
import com.vlibrovs.twentyfortyeight.data.model.theme.ThemePropertyType
import com.vlibrovs.twentyfortyeight.ui.common.composables.Button
import com.vlibrovs.twentyfortyeight.ui.common.composables.ColorCircle
import com.vlibrovs.twentyfortyeight.ui.common.composables.SecondaryBackgroundBox
import com.vlibrovs.twentyfortyeight.ui.common.fonts.Fonts
import com.vlibrovs.twentyfortyeight.ui.common.navigation.Screen
import com.vlibrovs.twentyfortyeight.ui.common.window.rememberWindowInfo
import com.vlibrovs.twentyfortyeight.ui.viewmodel.EditViewModel

@Composable
fun ThemeEditScreen(
    currentTheme: Theme,
    navController: NavController,
    editViewModel: EditViewModel
) {
    val values = getValues(rememberWindowInfo().screenWidthInfo)
    var themeName by remember {
        mutableStateOf(editViewModel.themeBuilder?.name ?: currentTheme.name)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(currentTheme.backgroundGradient.toList())
            )
            .padding(values.statsPadding),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SecondaryBackgroundBox(
            modifier = Modifier.fillMaxHeight(0.9f),
            theme = currentTheme,
            cornerRadius = values.boxCornerRadius
        ) {
            Column(
                modifier = Modifier
                    .padding(values.settingsInboxPadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    TextField(
                        value = themeName,
                        onValueChange = {
                            themeName = it
                            editViewModel.themeBuilder!!.name
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(50.dp),
                        textStyle = TextStyle(
                            color = currentTheme.textColor,
                            fontSize = values.buttonTextSize,
                            fontFamily = Fonts.Poppins
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = currentTheme.secondaryBackgroundColor,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                    if (themeName.isEmpty()) {
                        Text(
                            modifier = Modifier.fillMaxWidth(0.9f),
                            textAlign = TextAlign.Start,
                            text = stringResource(id = R.string.theme_name),
                            color = currentTheme.secondaryBackgroundColor,
                            fontSize = values.buttonTextSize,
                            fontFamily = Fonts.Poppins
                        )
                    }
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.85f)
                ) {
                    item {
                        PropertyItem(
                            theme = currentTheme,
                            name = stringResource(id = R.string.primary_background),
                            firstColor = editViewModel.themeBuilder!!.backgroundGradient.colorStart,
                            secondColor = editViewModel.themeBuilder!!.backgroundGradient.colorEnd,
                            navController = navController,
                            editViewModel = editViewModel
                        )
                    }
                    item {
                        PropertyItem(
                            theme = currentTheme,
                            name = stringResource(id = R.string.secondary_background),
                            color = editViewModel.themeBuilder!!.secondaryBackgroundColor,
                            navController = navController,
                            propertyType = ThemePropertyType.SecondaryBackgroundColor,
                            editViewModel = editViewModel
                        )
                    }
                    item {
                        PropertyItem(
                            theme = currentTheme,
                            name = stringResource(id = R.string.buttons),
                            color = editViewModel.themeBuilder!!.buttonColor,
                            navController = navController,
                            propertyType = ThemePropertyType.ButtonColor,
                            editViewModel = editViewModel
                        )
                    }
                    item {
                        PropertyItem(
                            theme = currentTheme,
                            name = stringResource(id = R.string.text),
                            color = editViewModel.themeBuilder!!.textColor,
                            navController = navController,
                            propertyType = ThemePropertyType.TextColor,
                            editViewModel = editViewModel
                        )
                    }
                    item {
                        PropertyItem(
                            theme = currentTheme,
                            name = stringResource(id = R.string.lines),
                            color = editViewModel.themeBuilder!!.linesColor,
                            navController = navController,
                            propertyType = ThemePropertyType.LinesColor,
                            editViewModel = editViewModel
                        )
                    }
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.edit_tiles_styles),
                    fontSize = values.buttonTextSize,
                    onClick = {
                        navController.navigate(Screen.TilesStyles.route)
                    },
                    theme = currentTheme
                )
            }
        }
        Button(
            text = stringResource(id = R.string.save),
            fontSize = values.buttonTextSize,
            onClick = {
                editViewModel.finish()
                navController.navigate(Screen.Settings.route)
            },
            theme = currentTheme
        )
    }
}

@Composable
fun PropertyItem(
    theme: Theme,
    name: String,
    color: Color,
    navController: NavController,
    propertyType: ThemePropertyType,
    editViewModel: EditViewModel
) {
    val values = getValues(rememberWindowInfo().screenWidthInfo)
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(0.6f),
            text = name,
            color = theme.textColor,
            fontFamily = Fonts.Poppins,
            fontWeight = FontWeight.SemiBold,
            fontSize = values.contentFontSize
        )
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = values.settingsInboxPadding.calculateTopPadding()),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            ColorCircle(
                fillColor = color,
                outlineColor = theme.linesColor,
                outlineWidth = values.colorCircleOutlineWidth,
                size = values.addThemeButtonSize,
                onClick = {
                    editViewModel.themePropertyType = propertyType
                    navController.navigate(Screen.ColorPicker.route)
                }
            )
        }
    }
}

@Composable
fun PropertyItem(
    theme: Theme,
    name: String,
    firstColor: Color,
    secondColor: Color,
    navController: NavController,
    editViewModel: EditViewModel
) {
    val values = getValues(rememberWindowInfo().screenWidthInfo)
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(0.6f),
            text = name,
            color = theme.textColor,
            fontFamily = Fonts.Poppins,
            fontWeight = FontWeight.SemiBold,
            fontSize = values.contentFontSize
        )
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = values.settingsInboxPadding.calculateTopPadding()),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            ColorCircle(
                fillColor = secondColor,
                outlineColor = theme.linesColor,
                outlineWidth = values.colorCircleOutlineWidth,
                size = values.addThemeButtonSize,
                onClick = {
                    editViewModel.themePropertyType = ThemePropertyType.BackgroundColor(ColorPosition.END)
                    navController.navigate(Screen.ColorPicker.route)
                }
            )
            Spacer(modifier = Modifier.width(values.settingsInboxPadding.calculateTopPadding()))
            ColorCircle(
                fillColor = firstColor,
                outlineColor = theme.linesColor,
                outlineWidth = values.colorCircleOutlineWidth,
                size = values.addThemeButtonSize,
                onClick = {
                    editViewModel.themePropertyType = ThemePropertyType.BackgroundColor(ColorPosition.START)
                    navController.navigate(Screen.ColorPicker.route)
                }
            )
        }
    }
}