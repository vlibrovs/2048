package com.vlibrovs.twentyfortyeight.ui.screens.gamescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vlibrovs.twentyfortyeight.data.model.Gradient
import com.vlibrovs.twentyfortyeight.ui.common.fonts.Fonts
import kotlin.math.pow

@Composable
fun Tile(
    modifier: Modifier = Modifier,
    styles: Map<Int, Gradient>,
    level: Int
) {
    Box(
        modifier = modifier.background(
            shape = RoundedCornerShape(15.dp),
            brush = Brush.verticalGradient(styles[level]!!.toList())
        ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = 2f.pow(level).toInt().toString(),
            color = Color.White,
            fontSize = 32.sp,
            fontFamily = Fonts.Poppins
        )
    }
}