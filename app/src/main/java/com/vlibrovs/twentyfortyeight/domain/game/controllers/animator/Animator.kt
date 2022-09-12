package com.vlibrovs.twentyfortyeight.domain.game.controllers.animator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.unit.Dp
import com.vlibrovs.twentyfortyeight.domain.game.model.game_state.GameState

abstract class Animator(
    animationDuration: Int,
    gameState: GameState
) {
    @Composable
    abstract fun horizontalAnimator(squareSize: Dp): Array<State<Dp>>

    @Composable
    abstract fun verticalAnimator(squareSize: Dp): Array<State<Dp>>
}
