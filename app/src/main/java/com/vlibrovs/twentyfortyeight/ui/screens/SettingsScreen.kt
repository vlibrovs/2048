package com.vlibrovs.twentyfortyeight.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vlibrovs.twentyfortyeight.R
import com.vlibrovs.twentyfortyeight.common.getValues
import com.vlibrovs.twentyfortyeight.data.model.theme.Theme
import com.vlibrovs.twentyfortyeight.ui.common.composables.Button
import com.vlibrovs.twentyfortyeight.ui.common.composables.SecondaryBackgroundBox
import com.vlibrovs.twentyfortyeight.ui.common.fonts.Fonts
import com.vlibrovs.twentyfortyeight.ui.common.navigation.Screen
import com.vlibrovs.twentyfortyeight.ui.common.window.rememberWindowInfo
import com.vlibrovs.twentyfortyeight.ui.viewmodel.EditViewModel
import com.vlibrovs.twentyfortyeight.ui.viewmodel.MainViewModel

@Composable
fun SettingsScreen(
    theme: Theme,
    themes: List<Theme>,
    navController: NavController,
    viewModel: MainViewModel,
    editViewModel: EditViewModel
) {
    val values = getValues(rememberWindowInfo().screenWidthInfo)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(theme.backgroundGradient.toList()))
            .padding(values.statsPadding),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.settings),
            color = theme.textColor,
            fontSize = values.defaultTitleFontSize,
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
        SecondaryBackgroundBox(
            modifier = Modifier
                .fillMaxWidth()
                .height(values.settingsBoxHeight),
            theme = theme,
            cornerRadius = values.boxCornerRadius
        ) {
            Column(
                modifier = Modifier
                    .padding(values.settingsInboxPadding)
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.themes),
                        color = theme.textColor,
                        fontSize = values.buttonTextSize,
                        fontFamily = Fonts.Poppins,
                        fontWeight = FontWeight.SemiBold
                    )
                    Box(
                        modifier = Modifier
                            .size(values.addThemeButtonSize)
                            .background(
                                shape = CircleShape,
                                color = theme.buttonColor
                            ).clickable {
                                editViewModel.themeBuilder = Theme.Builder()
                                navController.navigate(Screen.ThemeEdit.route)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.fillMaxSize(0.6f),
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = "Add a theme",
                            tint = theme.textColor
                        )
                    }
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(themes) {
                        ThemeItem(theme = it, navController = navController, viewModel, editViewModel)
                    }
                }
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(values.buttonHeight),
            text = stringResource(id = R.string.back),
            fontSize = values.buttonTextSize,
            onClick = { navController.navigate(Screen.MainMenu.route) },
            theme = theme
        )
    }
}

@Composable
fun ThemeItem(theme: Theme, navController: NavController, viewModel: MainViewModel, editViewModel: EditViewModel) {
    val isSelected = theme.id == viewModel.selectedThemeId.value
    val values = getValues(rememberWindowInfo().screenWidthInfo)
    Row(
        modifier = Modifier
            .padding(top = 15.dp)
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(50.dp),
                brush = Brush.horizontalGradient(theme.backgroundGradient.toList())
            )
            .padding(values.themeItemPadding)
            .clickable {
                if (isSelected) {
                    editViewModel.themeBuilder = theme.edit()
                    navController.navigate(Screen.ThemeEdit.route)
                }
                else viewModel.selectedThemeId.value = theme.id!!
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement =
        if (isSelected) Arrangement.SpaceBetween
        else Arrangement.Start
    ) {
        Text(
            text = theme.name,
            color = theme.textColor,
            fontSize = values.buttonTextSize,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.SemiBold,
            style = TextStyle(
                shadow = Shadow(
                    Color(0x30000000),
                    blurRadius = 4f,
                    offset = Offset(0f, 6f)
                )
            )
        )
        if (isSelected) {
            Box(
                modifier = Modifier
                    .size(values.addThemeButtonSize / 1.5f)
                    .background(
                        shape = CircleShape,
                        color = theme.buttonColor
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(0.6f),
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = "Theme is selected",
                    tint = theme.textColor
                )
            }
        }
    }
}