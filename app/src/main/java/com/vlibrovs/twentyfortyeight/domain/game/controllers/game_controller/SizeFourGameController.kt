package com.vlibrovs.twentyfortyeight.domain.game.controllers.game_controller

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.vlibrovs.twentyfortyeight.common.Constants
import com.vlibrovs.twentyfortyeight.data.entity.Game
import com.vlibrovs.twentyfortyeight.domain.game.controllers.animator.SizeFourAnimator
import com.vlibrovs.twentyfortyeight.domain.game.controllers.generator.Generator
import com.vlibrovs.twentyfortyeight.domain.game.controllers.move_controller.SizeFourMoveController
import com.vlibrovs.twentyfortyeight.domain.game.controllers.scheme_controller.SizeFourSchemeController
import com.vlibrovs.twentyfortyeight.domain.game.controllers.stats_controller.StatsController
import com.vlibrovs.twentyfortyeight.domain.game.model.game_state.SizeFourGameState
import kotlinx.coroutines.*
import java.util.*

class SizeFourGameController(coroutineScope: CoroutineScope, scoreState: MutableState<Int>) :
    GameController(coroutineScope) {

    private val _gameEnded = mutableStateOf(false)
    val gameEnded
        get() = _gameEnded
    override val statsController = StatsController(scoreState)
    override val gameState = SizeFourGameState()
    override val schemeController = SizeFourSchemeController(gameState)
    override val generator = Generator(gameState = gameState, gameEndState = _gameEnded)
    override val moveController = SizeFourMoveController(
        gameState,
        schemeController,
        generator,
        statsController,
        coroutineScope
    )
    override val animator =
        SizeFourAnimator(animationDuration = Constants.ANIMATION_DURATION, gameState = gameState)

    override fun endGame(): Game = Game(
        score = statsController.score.value,
        moves = statsController.moves.value,
        date = Date()
    )

    init {
        generator.generate()
    }
}