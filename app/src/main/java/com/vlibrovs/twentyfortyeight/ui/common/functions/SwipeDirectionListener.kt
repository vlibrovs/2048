package com.vlibrovs.twentyfortyeight.ui.common.functions

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import com.vlibrovs.twentyfortyeight.common.Constants
import com.vlibrovs.twentyfortyeight.data.model.Direction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.abs

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