package com.vlibrovs.twentyfortyeight.domain.game.controllers.game_controller

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.vlibrovs.twentyfortyeight.common.Constants
import com.vlibrovs.twentyfortyeight.data.model.game.FinishedGame
import com.vlibrovs.twentyfortyeight.data.model.game.Game
import com.vlibrovs.twentyfortyeight.data.model.game.UnfinishedGame
import com.vlibrovs.twentyfortyeight.domain.game.controllers.animator.SizeFourAnimator
import com.vlibrovs.twentyfortyeight.domain.game.controllers.generator.Generator
import com.vlibrovs.twentyfortyeight.domain.game.controllers.move_controller.SizeFourMoveController
import com.vlibrovs.twentyfortyeight.domain.game.controllers.scheme_controller.SizeFourSchemeController
import com.vlibrovs.twentyfortyeight.domain.game.controllers.stats_controller.StatsController
import com.vlibrovs.twentyfortyeight.domain.game.model.game_state.SizeFourGameState
import com.vlibrovs.twentyfortyeight.ui.viewmodel.MainViewModel
import kotlinx.coroutines.*
import java.util.*

class SizeFourGameController(
    coroutineScope: CoroutineScope,
    scoreState: MutableState<Int>,
    private val viewModel: MainViewModel,
    game: UnfinishedGame
) :
    GameController() {

    private val _gameEnded = mutableStateOf(false)
    val gameEnded
        get() = _gameEnded
    override val gameState =
        SizeFourGameState.fromString(game.extra)!!
    override val statsController = StatsController(
        scoreState.apply { value = game.score },
        game = game
    )
    override val schemeController = SizeFourSchemeController(gameState)
    override val generator = Generator(gameState = gameState, gameEndState = _gameEnded)
    override val moveController = SizeFourMoveController(
        gameState,
        schemeController,
        generator,
        statsController,
        coroutineScope,
        game = game,
        viewModel = viewModel
    )
    override val animator =
        SizeFourAnimator(animationDuration = Constants.ANIMATION_DURATION, gameState = gameState)

    override fun finish() {
        val game = FinishedGame(
            score = statsController.score.value,
            moves = statsController.moves.value,
            date = Date()
        )
        viewModel.saveGame(game)
    }

    override fun pause() {
        val game = UnfinishedGame(
            score = statsController.score.value,
            moves = statsController.moves.value,
            state = gameState
        )
        viewModel.saveGame(game)
    }

    init {
        if (game.score == 0)
            generator.generate()
    }
}