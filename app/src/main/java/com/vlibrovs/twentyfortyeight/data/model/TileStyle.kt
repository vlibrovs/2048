package com.vlibrovs.twentyfortyeight.data.model

import androidx.compose.ui.graphics.Color

data class TileStyle(
    val tileLevel: Int,
    var colorStart: Color = Color.White,
    var colorEnd: Color = Color.White
) {
    override fun toString(): String {
        return "/$tileLevel/${colorStart.value}/${colorEnd.value}/"
    }
}
