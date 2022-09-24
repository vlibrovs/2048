package com.vlibrovs.twentyfortyeight.domain.game.controllers.stats_controller

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.vlibrovs.twentyfortyeight.data.model.game.UnfinishedGame
import kotlin.math.pow

class StatsController(
    scoreState: MutableState<Int>,
    private val game: UnfinishedGame
) {
    val score = scoreState
    val moves = mutableStateOf(game.moves)

    fun addScore(score: Int) {
        this.score.value += score
        game.score += score
    }

    fun addScoreByLevel(level: Int) {
        val scoreDiff = 2f.pow(level).toInt()
        score.value += scoreDiff
        game.score += scoreDiff
    }

    fun makeMove() {
        moves.value++
        game.moves++
    }

}
