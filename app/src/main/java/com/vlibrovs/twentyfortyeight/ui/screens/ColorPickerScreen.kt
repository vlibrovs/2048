package com.vlibrovs.twentyfortyeight.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.skydoves.colorpicker.compose.AlphaSlider
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.vlibrovs.twentyfortyeight.R
import com.vlibrovs.twentyfortyeight.common.getValues
import com.vlibrovs.twentyfortyeight.data.model.theme.ColorPosition
import com.vlibrovs.twentyfortyeight.data.model.theme.Theme
import com.vlibrovs.twentyfortyeight.data.model.theme.ThemePropertyType
import com.vlibrovs.twentyfortyeight.ui.common.composables.Button
import com.vlibrovs.twentyfortyeight.ui.common.composables.SecondaryBackgroundBox
import com.vlibrovs.twentyfortyeight.ui.common.fonts.Fonts
import com.vlibrovs.twentyfortyeight.ui.common.navigation.Screen
import com.vlibrovs.twentyfortyeight.ui.common.window.rememberWindowInfo
import com.vlibrovs.twentyfortyeight.ui.viewmodel.EditViewModel
import org.koin.core.scope.ScopeID

@Composable
fun ColorPickerScreen(
    theme: Theme,
    navController: NavController,
    editViewModel: EditViewModel
) {
    val themePropertyType = editViewModel.themePropertyType!!
    val color = remember {
        when (themePropertyType) {
            is ThemePropertyType.BackgroundColor -> {
                editViewModel.themeBuilder!!.backgroundGradient.run {
                    if (themePropertyType.position == ColorPosition.START) colorStart else colorEnd
                }
            }
            is ThemePropertyType.ButtonColor -> editViewModel.themeBuilder!!.buttonColor
            is ThemePropertyType.LinesColor -> editViewModel.themeBuilder!!.linesColor
            is ThemePropertyType.SecondaryBackgroundColor -> editViewModel.themeBuilder!!.secondaryBackgroundColor
            is ThemePropertyType.TextColor -> editViewModel.themeBuilder!!.textColor
            is ThemePropertyType.TileColor -> editViewModel.themeBuilder!!.tileStyles[themePropertyType.level]!!.run {
                if (themePropertyType.colorPosition == ColorPosition.START) colorStart else colorEnd
            }
        }
    }
    val values = getValues(rememberWindowInfo().screenWidthInfo)
    val controller = rememberColorPickerController()
    var currentColor by remember {
        mutableStateOf(Color.White)
    }
    var currentColorString by remember {
        mutableStateOf("#FFFFFFFF")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(theme.backgroundGradient.toList())
            )
            .padding(values.statsPadding),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        SecondaryBackgroundBox(
            theme = theme,
            cornerRadius = values.boxCornerRadius,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    HsvColorPicker(
                        modifier = Modifier.size(values.colorPickerSize),
                        controller = controller,
                        onColorChanged = {
                            currentColor = it.color
                            currentColorString = it.hexCode
                        }
                    )
                    BrightnessSlider(
                        modifier = Modifier
                            .width(values.colorPickerSize)
                            .height(values.sliderHeight), controller = controller,
                        wheelRadius = values.sliderHeight - 2.dp,
                        wheelColor = theme.buttonColor
                    )
                    AlphaSlider(
                        modifier = Modifier
                            .width(values.colorPickerSize)
                            .height(values.sliderHeight), controller = controller,
                        wheelRadius = values.sliderHeight - 2.dp,
                        wheelColor = theme.buttonColor
                    )
                    Row(
                        modifier = Modifier.width(values.colorPickerSize),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = color.toHex(),
                                color = color,
                                fontSize = values.colorStringSize,
                                fontFamily = Fonts.Poppins,
                                fontWeight = FontWeight.SemiBold
                            )
                            Box(
                                modifier = Modifier
                                    .size(values.tileStyleSize)
                                    .background(
                                        color = color,
                                        shape = RoundedCornerShape(values.tileStyleCornerRadius)
                                    )
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "#$currentColorString",
                                color = currentColor,
                                fontSize = values.colorStringSize,
                                fontFamily = Fonts.Poppins,
                                fontWeight = FontWeight.SemiBold
                            )
                            Box(
                                modifier = Modifier
                                    .size(values.tileStyleSize)
                                    .background(
                                        color = currentColor,
                                        shape = RoundedCornerShape(values.tileStyleCornerRadius)
                                    )
                            )
                        }
                    }
                }

            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(values.buttonHeight),
            text = stringResource(id = R.string.save),
            fontSize = values.buttonTextSize,
            onClick = {
                when (themePropertyType) {
                    is ThemePropertyType.BackgroundColor -> {
                        editViewModel.themeBuilder!!.backgroundGradient.apply {
                            when (themePropertyType.position) {
                                ColorPosition.START -> colorStart = currentColor
                                ColorPosition.END -> colorEnd = currentColor
                            }
                        }
                        navController.navigate(Screen.ThemeEdit.route)
                    }
                    is ThemePropertyType.ButtonColor -> {
                        editViewModel.themeBuilder!!.buttonColor = currentColor
                        navController.navigate(Screen.ThemeEdit.route)
                    }
                    is ThemePropertyType.LinesColor -> {
                        editViewModel.themeBuilder!!.linesColor = currentColor
                        navController.navigate(Screen.ThemeEdit.route)
                    }
                    is ThemePropertyType.SecondaryBackgroundColor -> {
                        editViewModel.themeBuilder!!.secondaryBackgroundColor = currentColor
                        navController.navigate(Screen.ThemeEdit.route)
                    }
                    is ThemePropertyType.TextColor -> {
                        editViewModel.themeBuilder!!.textColor = currentColor
                        navController.navigate(Screen.ThemeEdit.route)
                    }
                    is ThemePropertyType.TileColor -> {
                        editViewModel.themeBuilder!!.tileStyles[themePropertyType.level]!!.apply {
                            when (themePropertyType.colorPosition) {
                                ColorPosition.START -> colorStart = currentColor
                                ColorPosition.END -> colorEnd = currentColor
                            }
                        }
                        navController.navigate(Screen.TilesStyles.route)
                    }
                }
            },
            theme = theme
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(values.buttonHeight),
            text = stringResource(id = R.string.cancel),
            fontSize = values.buttonTextSize,
            onClick = {
                navController.navigate(
                    if (themePropertyType is ThemePropertyType.TileColor)
                        Screen.TilesStyles.route else Screen.ThemeEdit.route
                )
            },
            theme = theme
        )
    }
}

private fun Color.toHex(): String {
    val alpha = this.alpha * 255
    val red = this.red * 255
    val green = this.green * 255
    val blue = this.blue * 255
    return "#${alpha.toInt().toString(16)}${red.toInt().toString(16)}${
        green.toInt().toString(16)
    }${blue.toInt().toString(16)}".uppercase()
}