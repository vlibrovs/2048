package com.vlibrovs.twentyfortyeight.ui.screens.gamescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vlibrovs.twentyfortyeight.common.getValues
import com.vlibrovs.twentyfortyeight.data.model.Gradient
import com.vlibrovs.twentyfortyeight.data.model.theme.Theme
import com.vlibrovs.twentyfortyeight.ui.common.fonts.Fonts
import com.vlibrovs.twentyfortyeight.ui.common.window.rememberWindowInfo
import kotlin.math.pow

@Composable
fun Tile(
    theme: Theme,
    modifier: Modifier = Modifier,
    styles: Map<Int, Gradient>,
    level: Int?
) {
    val values = getValues(rememberWindowInfo().screenWidthInfo)
    if (level != null) {
        Box(
            modifier = modifier.background(
                shape = RoundedCornerShape(15.dp),
                brush = Brush.verticalGradient(styles[level]!!.toList())
            ),
            contentAlignment = Alignment.Center
        ) {
            val text = 2f.pow(level).toInt().toString()
            Text(
                text = text,
                color = theme.textColor,
                fontSize = values.tileTextSize[text.length]!!,
                fontFamily = Fonts.Poppins,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    shadow = Shadow(
                        Color(0x30000000),
                        blurRadius = 4f,
                        offset = Offset(0f, 6f)
                    ),
                    fontFamily = Fonts.Poppins,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}