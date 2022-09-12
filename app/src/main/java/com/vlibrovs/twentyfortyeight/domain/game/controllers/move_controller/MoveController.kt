package com.vlibrovs.twentyfortyeight.domain.game.controllers.move_controller

import com.vlibrovs.twentyfortyeight.domain.game.controllers.generator.Generator
import com.vlibrovs.twentyfortyeight.domain.game.controllers.scheme_controller.SchemeController
import com.vlibrovs.twentyfortyeight.domain.game.controllers.stats_controller.StatsController
import com.vlibrovs.twentyfortyeight.domain.game.model.game_state.GameState
import kotlinx.coroutines.CoroutineScope

abstract class MoveController(
    private val gameState: GameState,
    private val schemeController: SchemeController,
    private val generator: Generator,
    private val statsController: StatsController,
    private val coroutineScope: CoroutineScope
) {
    abstract fun moveRight()
    abstract fun moveLeft()
    abstract fun moveUp()
    abstract fun moveDown()
}
