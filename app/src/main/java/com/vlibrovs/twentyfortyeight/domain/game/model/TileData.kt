package com.vlibrovs.twentyfortyeight.domain.game.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class TileData(
    val level: MutableState<Int?>,
    val positionX: MutableState<Int>,
    val positionY: MutableState<Int>,
    val justCreated: MutableState<Boolean> = mutableStateOf(false)
)
