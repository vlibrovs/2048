package com.vlibrovs.twentyfortyeight.domain.game.controllers.game_controller

import com.vlibrovs.twentyfortyeight.data.model.game.Game
import com.vlibrovs.twentyfortyeight.domain.game.controllers.animator.Animator
import com.vlibrovs.twentyfortyeight.domain.game.controllers.generator.Generator
import com.vlibrovs.twentyfortyeight.domain.game.controllers.move_controller.MoveController
import com.vlibrovs.twentyfortyeight.domain.game.controllers.scheme_controller.SchemeController
import com.vlibrovs.twentyfortyeight.domain.game.controllers.stats_controller.StatsController
import com.vlibrovs.twentyfortyeight.domain.game.model.game_state.GameState
import kotlinx.coroutines.CoroutineScope

abstract class GameController(private val coroutineScope: CoroutineScope) {
    abstract val gameState: GameState
    abstract val schemeController: SchemeController
    abstract val moveController: MoveController
    abstract val statsController: StatsController
    abstract val generator: Generator
    abstract val animator: Animator

    abstract fun endGame(): Game
}