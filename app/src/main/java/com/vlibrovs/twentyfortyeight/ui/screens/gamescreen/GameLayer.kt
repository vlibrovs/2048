package com.vlibrovs.twentyfortyeight.ui.screens.gamescreen

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.swipeable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vlibrovs.twentyfortyeight.common.Constants
import com.vlibrovs.twentyfortyeight.data.model.Direction
import com.vlibrovs.twentyfortyeight.data.model.Gradient
import com.vlibrovs.twentyfortyeight.data.model.Theme
import com.vlibrovs.twentyfortyeight.domain.game.GameController
import com.vlibrovs.twentyfortyeight.domain.game.TileData
import com.vlibrovs.twentyfortyeight.ui.common.fonts.Fonts
import kotlinx.coroutines.*
import java.lang.Exception
import java.lang.NullPointerException
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.roundToInt

const val TAG = "GameLayer"

@Composable
fun GameLayer(
    modifier: Modifier = Modifier,
    innerPadding: Dp,
    theme: Theme,
    squareSize: Dp
) {
    val scope = rememberCoroutineScope()
    val controller = remember {
        GameController(scope)
    }
    val animatorX = controller.getAnimatorX(
        squareSize = squareSize,
        animationDuration = Constants.ANIMATION_DURATION
    )
    val animatorY = controller.getAnimatorY(
        squareSize = squareSize,
        animationDuration = Constants.ANIMATION_DURATION
    )
    val swipeDirection = remember {
        mutableStateOf(Direction.UNIT)
    }
    val allowMove = remember {
        mutableStateOf(true)
    }
    BoxWithConstraints(
        modifier = modifier
            .swipeDirectionListener(
                directionState = swipeDirection,
                allowMoveState = allowMove,
                coroutineScope = scope
            ) { direction ->
                when (direction) {
                    Direction.RIGHT -> try {
                        controller.moveRight()
                        Log.d(TAG, "GameLayer: Successfully executed GameController#moveRight()")
                    } catch (e: NullPointerException) {
                        Log.d(
                            TAG,
                            "GameLayer: Exception caught executing GameController#moveRight()"
                        )
                    }
                    Direction.UP -> try {
                        controller.moveUp()
                        Log.d(TAG, "GameLayer: Successfully executed GameController#moveUp()")
                    } catch (e: NullPointerException) {
                        Log.d(TAG, "GameLayer: Exception caught executing GameController#moveUp()")
                    }
                    Direction.DOWN -> try {
                        controller.moveDown()
                        Log.d(TAG, "GameLayer: Successfully executed GameController#moveDown()")
                    } catch (e: NullPointerException) {
                        Log.d(
                            TAG,
                            "GameLayer: Exception caught executing GameController#moveDown()"
                        )
                    }
                    Direction.LEFT -> try {
                        controller.moveLeft()
                        Log.d(TAG, "GameLayer: Successfully executed GameController#moveLeft()")
                    } catch (e: NullPointerException) {
                        Log.d(
                            TAG,
                            "GameLayer: Exception caught executing GameController#moveLeft()"
                        )
                    }
                    Direction.UNIT -> Unit
                }
            }
    ) {
        if (controller.gameState[0].level.value != null) {
            Tile(
                modifier = Modifier
                    .padding(
                        start = innerPadding +
                                if (controller.gameState[0].justCreated.value) {
                                    squareSize * controller.gameState[0].positionX.value
                                } else animatorX[0].value,
                        top = innerPadding +
                                if (controller.gameState[0].justCreated.value) {
                                    squareSize * controller.gameState[0].positionY.value
                                } else animatorY[0].value,
                    )
                    .size(squareSize - innerPadding * 2),
                styles = theme.tileStyles,
                level = controller.gameState[0].level.value!!
            )
        }
        if (controller.gameState[1].level.value != null) {
            Tile(
                modifier = Modifier
                    .padding(
                        start = innerPadding +
                                if (controller.gameState[1].justCreated.value) {
                                    squareSize * controller.gameState[1].positionX.value
                                } else animatorX[1].value,
                        top = innerPadding +
                                if (controller.gameState[1].justCreated.value) {
                                    squareSize * controller.gameState[1].positionY.value
                                } else animatorY[1].value,
                    )
                    .size(squareSize - innerPadding * 2),
                styles = theme.tileStyles,
                level = controller.gameState[1].level.value!!
            )
        }
        if (controller.gameState[2].level.value != null) {
            Tile(
                modifier = Modifier
                    .padding(
                        start = innerPadding +
                                if (controller.gameState[2].justCreated.value) {
                                    squareSize * controller.gameState[2].positionX.value
                                } else animatorX[2].value,
                        top = innerPadding +
                                if (controller.gameState[2].justCreated.value) {
                                    squareSize * controller.gameState[2].positionY.value
                                } else animatorY[2].value,
                    )
                    .size(squareSize - innerPadding * 2),
                styles = theme.tileStyles,
                level = controller.gameState[2].level.value!!
            )
        }
        if (controller.gameState[3].level.value != null) {
            Tile(
                modifier = Modifier
                    .padding(
                        start = innerPadding +
                                if (controller.gameState[3].justCreated.value) {
                                    squareSize * controller.gameState[3].positionX.value
                                } else animatorX[3].value,
                        top = innerPadding +
                                if (controller.gameState[3].justCreated.value) {
                                    squareSize * controller.gameState[3].positionY.value
                                } else animatorY[3].value,
                    )
                    .size(squareSize - innerPadding * 2),
                styles = theme.tileStyles,
                level = controller.gameState[3].level.value!!
            )
        }
        if (controller.gameState[4].level.value != null) {
            Tile(
                modifier = Modifier
                    .padding(
                        start = innerPadding +
                                if (controller.gameState[4].justCreated.value) {
                                    squareSize * controller.gameState[4].positionX.value
                                } else animatorX[4].value,
                        top = innerPadding +
                                if (controller.gameState[4].justCreated.value) {
                                    squareSize * controller.gameState[4].positionY.value
                                } else animatorY[4].value,
                    )
                    .size(squareSize - innerPadding * 2),
                styles = theme.tileStyles,
                level = controller.gameState[4].level.value!!
            )
        }
        if (controller.gameState[5].level.value != null) {
            Tile(
                modifier = Modifier
                    .padding(
                        start = innerPadding +
                                if (controller.gameState[5].justCreated.value) {
                                    squareSize * controller.gameState[5].positionX.value
                                } else animatorX[5].value,
                        top = innerPadding +
                                if (controller.gameState[5].justCreated.value) {
                                    squareSize * controller.gameState[5].positionY.value
                                } else animatorY[5].value,
                    )
                    .size(squareSize - innerPadding * 2),
                styles = theme.tileStyles,
                level = controller.gameState[5].level.value!!
            )
        }
        if (controller.gameState[6].level.value != null) {
            Tile(
                modifier = Modifier
                    .padding(
                        start = innerPadding +
                                if (controller.gameState[6].justCreated.value) {
                                    squareSize * controller.gameState[6].positionX.value
                                } else animatorX[6].value,
                        top = innerPadding +
                                if (controller.gameState[6].justCreated.value) {
                                    squareSize * controller.gameState[6].positionY.value
                                } else animatorY[6].value,
                    )
                    .size(squareSize - innerPadding * 2),
                styles = theme.tileStyles,
                level = controller.gameState[6].level.value!!
            )
        }
        if (controller.gameState[7].level.value != null) {
            Tile(
                modifier = Modifier
                    .padding(
                        start = innerPadding +
                                if (controller.gameState[7].justCreated.value) {
                                    squareSize * controller.gameState[7].positionX.value
                                } else animatorX[7].value,
                        top = innerPadding +
                                if (controller.gameState[7].justCreated.value) {
                                    squareSize * controller.gameState[7].positionY.value
                                } else animatorY[7].value,
                    )
                    .size(squareSize - innerPadding * 2),
                styles = theme.tileStyles,
                level = controller.gameState[7].level.value!!
            )
        }
        if (controller.gameState[8].level.value != null) {
            Tile(
                modifier = Modifier
                    .padding(
                        start = innerPadding +
                                if (controller.gameState[8].justCreated.value) {
                                    squareSize * controller.gameState[8].positionX.value
                                } else animatorX[8].value,
                        top = innerPadding +
                                if (controller.gameState[8].justCreated.value) {
                                    squareSize * controller.gameState[8].positionY.value
                                } else animatorY[8].value,
                    )
                    .size(squareSize - innerPadding * 2),
                styles = theme.tileStyles,
                level = controller.gameState[8].level.value!!
            )
        }
        if (controller.gameState[9].level.value != null) {
            Tile(
                modifier = Modifier
                    .padding(
                        start = innerPadding +
                                if (controller.gameState[9].justCreated.value) {
                                    squareSize * controller.gameState[9].positionX.value
                                } else animatorX[9].value,
                        top = innerPadding +
                                if (controller.gameState[9].justCreated.value) {
                                    squareSize * controller.gameState[9].positionY.value
                                } else animatorY[9].value,
                    )
                    .size(squareSize - innerPadding * 2),
                styles = theme.tileStyles,
                level = controller.gameState[9].level.value!!
            )
        }
        if (controller.gameState[10].level.value != null) {
            Tile(
                modifier = Modifier
                    .padding(
                        start = innerPadding +
                                if (controller.gameState[10].justCreated.value) {
                                    squareSize * controller.gameState[10].positionX.value
                                } else animatorX[10].value,
                        top = innerPadding +
                                if (controller.gameState[10].justCreated.value) {
                                    squareSize * controller.gameState[10].positionY.value
                                } else animatorY[10].value,
                    )
                    .size(squareSize - innerPadding * 2),
                styles = theme.tileStyles,
                level = controller.gameState[10].level.value!!
            )
        }
        if (controller.gameState[11].level.value != null) {
            Tile(
                modifier = Modifier
                    .padding(
                        start = innerPadding +
                                if (controller.gameState[11].justCreated.value) {
                                    squareSize * controller.gameState[11].positionX.value
                                } else animatorX[11].value,
                        top = innerPadding +
                                if (controller.gameState[11].justCreated.value) {
                                    squareSize * controller.gameState[11].positionY.value
                                } else animatorY[11].value,
                    )
                    .size(squareSize - innerPadding * 2),
                styles = theme.tileStyles,
                level = controller.gameState[11].level.value!!
            )
        }
        if (controller.gameState[12].level.value != null) {
            Tile(
                modifier = Modifier
                    .padding(
                        start = innerPadding +
                                if (controller.gameState[12].justCreated.value) {
                                    squareSize * controller.gameState[12].positionX.value
                                } else animatorX[12].value,
                        top = innerPadding +
                                if (controller.gameState[12].justCreated.value) {
                                    squareSize * controller.gameState[12].positionY.value
                                } else animatorY[12].value,
                    )
                    .size(squareSize - innerPadding * 2),
                styles = theme.tileStyles,
                level = controller.gameState[12].level.value!!
            )
        }
        if (controller.gameState[13].level.value != null) {
            Tile(
                modifier = Modifier
                    .padding(
                        start = innerPadding +
                                if (controller.gameState[13].justCreated.value) {
                                    squareSize * controller.gameState[13].positionX.value
                                } else animatorX[13].value,
                        top = innerPadding +
                                if (controller.gameState[13].justCreated.value) {
                                    squareSize * controller.gameState[13].positionY.value
                                } else animatorY[13].value,
                    )
                    .size(squareSize - innerPadding * 2),
                styles = theme.tileStyles,
                level = controller.gameState[13].level.value!!
            )
        }
        if (controller.gameState[14].level.value != null) {
            Tile(
                modifier = Modifier
                    .padding(
                        start = innerPadding +
                                if (controller.gameState[14].justCreated.value) {
                                    squareSize * controller.gameState[14].positionX.value
                                } else animatorX[14].value,
                        top = innerPadding +
                                if (controller.gameState[14].justCreated.value) {
                                    squareSize * controller.gameState[14].positionY.value
                                } else animatorY[14].value,
                    )
                    .size(squareSize - innerPadding * 2),
                styles = theme.tileStyles,
                level = controller.gameState[14].level.value!!
            )
        }
        if (controller.gameState[15].level.value != null) {
            Tile(
                modifier = Modifier
                    .padding(
                        start = innerPadding +
                                if (controller.gameState[15].justCreated.value) {
                                    squareSize * controller.gameState[15].positionX.value
                                } else animatorX[15].value,
                        top = innerPadding +
                                if (controller.gameState[15].justCreated.value) {
                                    squareSize * controller.gameState[15].positionY.value
                                } else animatorY[15].value,
                    )
                    .size(squareSize - innerPadding * 2),
                styles = theme.tileStyles,
                level = controller.gameState[15].level.value!!
            )
        }
    }
}

@Composable
fun Tile(
    modifier: Modifier = Modifier,
    styles: Map<Int, Gradient>,
    level: Int
) {
    Box(
        modifier = modifier.background(
            shape = RoundedCornerShape(15.dp),
            brush = Brush.verticalGradient(styles[level]!!.toList())
        ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = 2f.pow(level).toInt().toString(),
            color = Color.White,
            fontSize = 32.sp,
            fontFamily = Fonts.Poppins
        )
    }
}

fun Modifier.swipeDirectionListener(
    directionState: MutableState<Direction>,
    allowMoveState: MutableState<Boolean>,
    coroutineScope: CoroutineScope,
    onSwipeEnd: (Direction) -> Unit
): Modifier {
    return this.pointerInput(Unit) {
        detectDragGestures(
            onDrag = { change, dragAmount ->
                change.consume()
                val (x, y) = dragAmount
                if (abs(x) > abs(y)) {
                    when {
                        x > 0 -> {
                            //right
                            directionState.value = Direction.RIGHT
                        }
                        x < 0 -> {
                            // left
                            directionState.value = Direction.LEFT
                        }
                    }
                } else {
                    when {
                        y > 0 -> {
                            // down
                            directionState.value = Direction.DOWN
                        }
                        y < 0 -> {
                            // up
                            directionState.value = Direction.UP
                        }
                    }
                }
            },
            onDragEnd = {
                if (allowMoveState.value) {
                    onSwipeEnd(directionState.value)
                    directionState.value = Direction.UNIT
                    coroutineScope.launch(Dispatchers.Default) {
                        allowMoveState.value = false
                        delay(timeMillis = Constants.ANIMATION_DURATION*2L)
                        allowMoveState.value = true
                    }
                }
            })
    }
}