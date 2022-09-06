package com.vlibrovs.twentyfortyeight.data.model

import androidx.compose.ui.graphics.Color

data class Gradient(
    val colorStart: Color,
    val colorEnd: Color
) {
    fun toList() = listOf(colorStart, colorEnd)
}
