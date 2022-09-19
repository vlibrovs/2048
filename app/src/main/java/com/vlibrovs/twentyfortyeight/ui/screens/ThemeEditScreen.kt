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
import androidx.compose.ui.graphics.toArgb
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
import com.vlibrovs.twentyfortyeight.ui.common.composables.ColorCircle
import com.vlibrovs.twentyfortyeight.ui.common.composables.SecondaryBackgroundBox
import com.vlibrovs.twentyfortyeight.ui.common.fonts.Fonts
import com.vlibrovs.twentyfortyeight.ui.common.navigation.Screen
import com.vlibrovs.twentyfortyeight.ui.common.window.rememberWindowInfo

@Composable
fun ThemeEditScreen(
    currentTheme: Theme,
    editTheme: Theme?,
    navController: NavController
) {
    val values = getValues(rememberWindowInfo().screenWidthInfo)
    var themeName by remember {
        mutableStateOf(editTheme?.name ?: "")
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
                        onValueChange = { themeName = it },
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
                            color = (editTheme ?: currentTheme).backgroundGradient.colorStart,
                            secondColor = (editTheme ?: currentTheme).backgroundGradient.colorEnd,
                            navController = navController
                        )
                    }
                    item {
                        PropertyItem(
                            theme = currentTheme,
                            name = stringResource(id = R.string.secondary_background),
                            color = (editTheme ?: currentTheme).secondaryBackgroundColor,
                            navController = navController
                        )
                    }
                    item {
                        PropertyItem(
                            theme = currentTheme,
                            name = stringResource(id = R.string.buttons),
                            color = (editTheme ?: currentTheme).buttonColor,
                            navController = navController
                        )
                    }
                    item {
                        PropertyItem(
                            theme = currentTheme,
                            name = stringResource(id = R.string.text),
                            color = (editTheme ?: currentTheme).textColor,
                            navController = navController
                        )
                    }
                    item {
                        PropertyItem(
                            theme = currentTheme,
                            name = stringResource(id = R.string.lines),
                            color = (editTheme ?: currentTheme).linesColor,
                            navController = navController
                        )
                    }
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.edit_tiles_styles),
                    fontSize = values.buttonTextSize,
                    onClick = { navController.navigate(Screen.TilesStyles.route+"?editTheme=${editTheme?.name}") },
                    theme = currentTheme
                )
            }
        }
        Button(
            text = stringResource(id = R.string.save),
            fontSize = values.buttonTextSize,
            onClick = { navController.navigate(Screen.Settings.route) },
            theme = currentTheme
        )
    }
}

@Composable
fun PropertyItem(
    theme: Theme,
    name: String,
    color: Color,
    secondColor: Color? = null,
    navController: NavController
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
            if (secondColor != null) {
                ColorCircle(
                    fillColor = secondColor,
                    outlineColor = theme.linesColor,
                    outlineWidth = values.colorCircleOutlineWidth,
                    size = values.addThemeButtonSize,
                    onClick = {
                        navController.navigate(Screen.ColorPicker.route+"/${secondColor.toArgb()}/${Screen.ThemeEdit.route}")
                    }
                )
                Spacer(modifier = Modifier.width(values.settingsInboxPadding.calculateTopPadding()))
            }
            ColorCircle(
                fillColor = color,
                outlineColor = theme.linesColor,
                outlineWidth = values.colorCircleOutlineWidth,
                size = values.addThemeButtonSize,
                onClick = {
                    navController.navigate(Screen.ColorPicker.route+"/${color.toArgb()}/${Screen.ThemeEdit.route}")
                }
            )
        }
    }
}