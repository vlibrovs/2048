package com.vlibrovs.twentyfortyeight.domain.game.controllers.game_controller

import androidx.compose.runtime.mutableStateOf
import com.vlibrovs.twentyfortyeight.common.Constants
import com.vlibrovs.twentyfortyeight.domain.game.controllers.animator.SizeFourAnimator
import com.vlibrovs.twentyfortyeight.domain.game.controllers.generator.Generator
import com.vlibrovs.twentyfortyeight.domain.game.controllers.move_controller.SizeFourMoveController
import com.vlibrovs.twentyfortyeight.domain.game.controllers.scheme_controller.SizeFourSchemeController
import com.vlibrovs.twentyfortyeight.domain.game.controllers.stats_controller.StatsController
import com.vlibrovs.twentyfortyeight.domain.game.model.game_state.SizeFourGameState
import kotlinx.coroutines.*

class SizeFourGameController(coroutineScope: CoroutineScope) :
    GameController(coroutineScope) {

    private val _gameEnded = mutableStateOf(false)
    val gameEnded
        get() = _gameEnded
    override val statsController = StatsController()
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

    init {
        generator.generate()
    }
}