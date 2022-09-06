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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.skydoves.colorpicker.compose.AlphaSlider
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.vlibrovs.twentyfortyeight.R
import com.vlibrovs.twentyfortyeight.common.getValues
import com.vlibrovs.twentyfortyeight.data.model.Theme
import com.vlibrovs.twentyfortyeight.ui.common.composables.Button
import com.vlibrovs.twentyfortyeight.ui.common.composables.SecondaryBackgroundBox
import com.vlibrovs.twentyfortyeight.ui.common.fonts.Fonts
import com.vlibrovs.twentyfortyeight.ui.common.window.rememberWindowInfo

@Composable
fun ColorPickerScreen(
    theme: Theme,
    color: Color,
    navController: NavController,
    entryRoute: String
) {
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
                navController.navigate(entryRoute.replace('*', '/'))
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
                navController.navigate(entryRoute.replace('*', '/'))
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