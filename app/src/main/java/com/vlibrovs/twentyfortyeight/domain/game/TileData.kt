package com.vlibrovs.twentyfortyeight.domain.game

import androidx.compose.runtime.MutableState

data class TileData(
    val level: MutableState<Int?>,
    val positionX: MutableState<Int>,
    val positionY: MutableState<Int>
)
