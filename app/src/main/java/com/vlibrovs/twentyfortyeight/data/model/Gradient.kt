package com.vlibrovs.twentyfortyeight.data.model

import androidx.compose.ui.graphics.Color

data class Gradient(
    var colorStart: Color,
    var colorEnd: Color
) {
    fun toList() = listOf(colorStart, colorEnd)
}
