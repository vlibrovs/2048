package com.vlibrovs.twentyfortyeight.domain.game.controllers.stats_controller

import androidx.compose.runtime.mutableStateOf

class StatsController(
    scoreValue: Int = 0,
    movesValue: Int = 0,
) {
    val score = mutableStateOf(scoreValue)
    val moves = mutableStateOf(movesValue)
}
