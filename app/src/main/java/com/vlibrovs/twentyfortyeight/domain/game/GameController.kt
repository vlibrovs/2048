package com.vlibrovs.twentyfortyeight.domain.game

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Dp
import java.lang.StringBuilder

class GameController {
    val gameState = arrayOf(
        TileData(
            level = mutableStateOf(1),
            positionX = mutableStateOf(0),
            positionY = mutableStateOf(0)
        ),
        TileData(
            level = mutableStateOf(1),
            positionX = mutableStateOf(1),
            positionY = mutableStateOf(0)
        ),
        TileData(
            level = mutableStateOf(1),
            positionX = mutableStateOf(2),
            positionY = mutableStateOf(0)
        ),
        TileData(
            level = mutableStateOf(1),
            positionX = mutableStateOf(3),
            positionY = mutableStateOf(0)
        ),
        TileData(
            level = mutableStateOf(null),
            positionX = mutableStateOf(0),
            positionY = mutableStateOf(1)
        ),
        TileData(
            level = mutableStateOf(null),
            positionX = mutableStateOf(1),
            positionY = mutableStateOf(1)
        ),
        TileData(
            level = mutableStateOf(null),
            positionX = mutableStateOf(2),
            positionY = mutableStateOf(1)
        ),
        TileData(
            level = mutableStateOf(null),
            positionX = mutableStateOf(3),
            positionY = mutableStateOf(1)
        ),
        TileData(
            level = mutableStateOf(null),
            positionX = mutableStateOf(0),
            positionY = mutableStateOf(2)
        ),
        TileData(
            level = mutableStateOf(null),
            positionX = mutableStateOf(1),
            positionY = mutableStateOf(2)
        ),
        TileData(
            level = mutableStateOf(null),
            positionX = mutableStateOf(2),
            positionY = mutableStateOf(2)
        ),
        TileData(
            level = mutableStateOf(null),
            positionX = mutableStateOf(3),
            positionY = mutableStateOf(2)
        ),
        TileData(
            level = mutableStateOf(null),
            positionX = mutableStateOf(0),
            positionY = mutableStateOf(3)
        ),
        TileData(
            level = mutableStateOf(null),
            positionX = mutableStateOf(1),
            positionY = mutableStateOf(3)
        ),
        TileData(
            level = mutableStateOf(null),
            positionX = mutableStateOf(2),
            positionY = mutableStateOf(3)
        ),
        TileData(
            level = mutableStateOf(null),
            positionX = mutableStateOf(3),
            positionY = mutableStateOf(3)
        )
    )

    private fun getScheme(): Array<Array<TileData>> {
        val array: Array<Array<TileData?>> = Array(4) {
            Array(4) {
                null
            }
        }
        for (tileData in gameState) {
            array[tileData.positionY.value][tileData.positionX.value] = tileData
        }
        return array.map { row -> row.map { item -> item!! }.toTypedArray() }.toTypedArray()
    }

    fun moveRight() {
        val scheme = getScheme()
        for (row in scheme) {
            when (String(CharArray(4) { index -> if (row[index].level.value == null) '0' else '1' })) {
                "1111" -> {
                    if (row[3].level.value == row[2].level.value && row[0].level.value == row[1].level.value) {
//                        row[3].level.value!!.inc()
                        row[2].positionX.value++
                        row[1].positionX.value++
                        row[0].positionX.value += 2
                    }
                }

                "1110" -> {
                    TODO()
                }
                "0111" -> {
                    TODO()
                }
                "1011" -> {
                    TODO()
                }
                "1101" -> {
                    TODO()
                }

                "1100" -> {
                    TODO()
                }
                "1010" -> {
                    TODO()
                }
                "1001" -> {
                    TODO()
                }
                "0101" -> {
                    TODO()
                }
                "0110" -> {
                    TODO()
                }
                "0011" -> {
                    TODO()
                }

                "1000" -> {
                    TODO()
                }
                "0100" -> {
                    TODO()
                }
                "0010" -> {
                    TODO()
                }
            }

        }
    }

    @Composable
    fun getAnimatorX(squareSize: Dp, animationDuration: Int): Array<State<Dp>> {
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
    fun getAnimatorY(squareSize: Dp, animationDuration: Int): Array<State<Dp>> {
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