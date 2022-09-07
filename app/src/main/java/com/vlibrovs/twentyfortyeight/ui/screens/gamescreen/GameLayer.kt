package com.vlibrovs.twentyfortyeight.ui.screens.gamescreen

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
import com.vlibrovs.twentyfortyeight.data.model.Direction
import com.vlibrovs.twentyfortyeight.data.model.Gradient
import com.vlibrovs.twentyfortyeight.data.model.Theme
import com.vlibrovs.twentyfortyeight.ui.common.fonts.Fonts
import kotlinx.coroutines.*
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.roundToInt

@Composable
fun GameLayer(
    modifier: Modifier = Modifier,
    innerPadding: Dp,
    theme: Theme,
    squareSize: Dp
) {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var level by remember {
        mutableStateOf(1)
    }
    var horizontal by remember {
        mutableStateOf(0)
    }
    var vertical by remember {
        mutableStateOf(0)
    }
    val horizontalAnimator by animateDpAsState(targetValue = squareSize * horizontal)
    val verticalAnimator by animateDpAsState(targetValue = squareSize * vertical)
    val swipeDirection = remember {
        mutableStateOf(Direction.UNIT)
    }
    BoxWithConstraints(
        modifier = modifier
            .swipeDirectionListener(swipeDirection) { direction ->
                when (direction) {
                    Direction.RIGHT -> if (horizontal in 0..2) horizontal++
                    Direction.UP -> if (vertical in 1..3) vertical--
                    Direction.DOWN -> if (vertical in 0..2) vertical++
                    Direction.LEFT -> if (horizontal in 1..3) horizontal--
                    Direction.UNIT -> Unit
                }
            }
    ) {
        Tile(
            modifier = Modifier
                .padding(
                    start = innerPadding + horizontalAnimator,
                    top = innerPadding + verticalAnimator
                )
                .size(squareSize - innerPadding * 2),
            styles = theme.tileStyles,
            level = level
        )
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