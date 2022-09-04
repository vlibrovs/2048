package com.vlibrovs.twentyfortyeight.ui.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.vlibrovs.twentyfortyeight.data.model.Theme
import com.vlibrovs.twentyfortyeight.ui.common.fonts.Fonts
import kotlin.math.pow

@Composable
fun Tile(
    level: Int,
    theme: Theme,
    size: Dp,
    cornerRadius: Dp,
    fontMap: Map<Int, TextUnit>
) {
    Box(
        modifier = Modifier
            .size(size)
            .background(
                shape = RoundedCornerShape(cornerRadius),
                brush = Brush.verticalGradient(
                    listOf(
                        theme.tileStyles[level - 1].colorStart,
                        theme.tileStyles[level - 1].colorEnd
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        val number = remember { 2f.pow(level).toInt() }
        Text(
            text = number.toString(),
            color = theme.textColor,
            fontSize = fontMap[number.toString().length] ?: 1.sp,
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