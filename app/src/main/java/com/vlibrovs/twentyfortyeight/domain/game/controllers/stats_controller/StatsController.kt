package com.vlibrovs.twentyfortyeight.domain.game.controllers.stats_controller

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlin.math.pow

class StatsController(
    scoreState: MutableState<Int>,
    movesValue: Int = 0,
) {
    val score = scoreState
    val moves = mutableStateOf(movesValue)

    fun addScore(score: Int) {
        this.score.value += score
    }

    fun addScoreByLevel(level: Int) {
        score.value += 2f.pow(level).toInt()
    }

}
