package com.vlibrovs.twentyfortyeight.domain.game.controllers.animator

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.unit.Dp
import com.vlibrovs.twentyfortyeight.domain.game.model.game_state.GameState
import com.vlibrovs.twentyfortyeight.domain.game.model.game_state.SizeFourGameState

class SizeFourAnimator(
    private val animationDuration: Int,
    private val gameState: SizeFourGameState
) : Animator(animationDuration, gameState) {
    @Composable
    override fun horizontalAnimator(squareSize: Dp): Array<State<Dp>> {
        val animatorX = Array<State<Dp>?>(16) { null }
        animatorX[0] = animateDpAsState(
            targetValue = squareSize * gameState[0].positionX.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorX[1] = animateDpAsState(
            targetValue = squareSize * gameState[1].positionX.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorX[2] = animateDpAsState(
            targetValue = squareSize * gameState[2].positionX.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorX[3] = animateDpAsState(
            targetValue = squareSize * gameState[3].positionX.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorX[4] = animateDpAsState(
            targetValue = squareSize * gameState[4].positionX.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorX[5] = animateDpAsState(
            targetValue = squareSize * gameState[5].positionX.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorX[6] = animateDpAsState(
            targetValue = squareSize * gameState[6].positionX.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorX[7] = animateDpAsState(
            targetValue = squareSize * gameState[7].positionX.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorX[8] = animateDpAsState(
            targetValue = squareSize * gameState[8].positionX.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorX[9] = animateDpAsState(
            targetValue = squareSize * gameState[9].positionX.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorX[10] = animateDpAsState(
            targetValue = squareSize * gameState[10].positionX.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorX[11] = animateDpAsState(
            targetValue = squareSize * gameState[11].positionX.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorX[12] = animateDpAsState(
            targetValue = squareSize * gameState[12].positionX.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorX[13] = animateDpAsState(
            targetValue = squareSize * gameState[13].positionX.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorX[14] = animateDpAsState(
            targetValue = squareSize * gameState[14].positionX.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorX[15] = animateDpAsState(
            targetValue = squareSize * gameState[15].positionX.value,
            animationSpec = tween(durationMillis = animationDuration)
        )

        return animatorX.map { state -> state!! }.toTypedArray()
    }

    @Composable
    override fun verticalAnimator(squareSize: Dp): Array<State<Dp>> {
        val animatorY = Array<State<Dp>?>(16) { null }
        animatorY[0] = animateDpAsState(
            targetValue = squareSize * gameState[0].positionY.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorY[1] = animateDpAsState(
            targetValue = squareSize * gameState[1].positionY.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorY[2] = animateDpAsState(
            targetValue = squareSize * gameState[2].positionY.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorY[3] = animateDpAsState(
            targetValue = squareSize * gameState[3].positionY.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorY[4] = animateDpAsState(
            targetValue = squareSize * gameState[4].positionY.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorY[5] = animateDpAsState(
            targetValue = squareSize * gameState[5].positionY.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorY[6] = animateDpAsState(
            targetValue = squareSize * gameState[6].positionY.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorY[7] = animateDpAsState(
            targetValue = squareSize * gameState[7].positionY.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorY[8] = animateDpAsState(
            targetValue = squareSize * gameState[8].positionY.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorY[9] = animateDpAsState(
            targetValue = squareSize * gameState[9].positionY.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorY[10] = animateDpAsState(
            targetValue = squareSize * gameState[10].positionY.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorY[11] = animateDpAsState(
            targetValue = squareSize * gameState[11].positionY.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorY[12] = animateDpAsState(
            targetValue = squareSize * gameState[12].positionY.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorY[13] = animateDpAsState(
            targetValue = squareSize * gameState[13].positionY.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorY[14] = animateDpAsState(
            targetValue = squareSize * gameState[14].positionY.value,
            animationSpec = tween(durationMillis = animationDuration)
        )
        animatorY[15] = animateDpAsState(
            targetValue = squareSize * gameState[15].positionY.value,
            animationSpec = tween(durationMillis = animationDuration)
        )

        return animatorY.map { state -> state!! }.toTypedArray()
    }
}