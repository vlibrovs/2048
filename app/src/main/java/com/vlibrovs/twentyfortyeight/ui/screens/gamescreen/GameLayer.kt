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
    val animatorX = controller.getAnimatorX(squareSize = squareSize, animationDuration = Constants.ANIMATION_DURATION)
    val animatorY = controller.getAnimatorY(squareSize = squareSize, animationDuration = Constants.ANIMATION_DURATION)
    val swipeDirection = remember {
        mutableStateOf(Direction.UNIT)
    }
    BoxWithConstraints(
        modifier = modifier
            .swipeDirectionListener(swipeDirection) { direction ->
                when (direction) {
                    Direction.RIGHT -> controller.moveRight()
                    Direction.UP -> controller.moveUp()
                    Direction.DOWN -> controller.moveDown()
                    Direction.LEFT -> controller.moveLeft()
                    Direction.UNIT -> Unit
                }
            }
    ) {
        if (controller.gameState[0].level.value != null) {
            Tile(
                modifier = Modifier
                    .padding(
                        start = innerPadding + animatorX[0].value,
                        top = innerPadding + animatorY[0].value
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
                        start = innerPadding + animatorX[1].value,
                        top = innerPadding + animatorY[1].value
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
                        start = innerPadding + animatorX[2].value,
                        top = innerPadding + animatorY[2].value
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
                        start = innerPadding + animatorX[3].value,
                        top = innerPadding + animatorY[3].value
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
                        start = innerPadding + animatorX[4].value,
                        top = innerPadding + animatorY[4].value
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
                        start = innerPadding + animatorX[5].value,
                        top = innerPadding + animatorY[5].value
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
                        start = innerPadding + animatorX[6].value,
                        top = innerPadding + animatorY[6].value
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
                        start = innerPadding + animatorX[7].value,
                        top = innerPadding + animatorY[7].value
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
                        start = innerPadding + animatorX[8].value,
                        top = innerPadding + animatorY[8].value
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
                        start = innerPadding + animatorX[9].value,
                        top = innerPadding + animatorY[9].value
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
                        start = innerPadding + animatorX[10].value,
                        top = innerPadding + animatorY[10].value
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
                        start = innerPadding + animatorX[11].value,
                        top = innerPadding + animatorY[11].value
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
                        start = innerPadding + animatorX[12].value,
                        top = innerPadding + animatorY[12].value
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
                        start = innerPadding + animatorX[13].value,
                        top = innerPadding + animatorY[13].value
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
                        start = innerPadding + animatorX[14].value,
                        top = innerPadding + animatorY[14].value
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
                        start = innerPadding + animatorX[15].value,
                        top = innerPadding + animatorY[15].value
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
                onSwipeEnd(directionState.value)
                directionState.value = Direction.UNIT
            })
    }
}