package com.vlibrovs.twentyfortyeight.ui.screens.gamescreen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.vlibrovs.twentyfortyeight.common.Constants
import com.vlibrovs.twentyfortyeight.data.model.Direction
import com.vlibrovs.twentyfortyeight.data.model.game.UnfinishedGame
import com.vlibrovs.twentyfortyeight.data.model.theme.Theme
import com.vlibrovs.twentyfortyeight.domain.game.controllers.game_controller.SizeFourGameController
import com.vlibrovs.twentyfortyeight.domain.game.model.game_state.SizeFourGameState
import com.vlibrovs.twentyfortyeight.ui.common.functions.swipeDirectionListener
import com.vlibrovs.twentyfortyeight.ui.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import java.lang.NullPointerException

const val TAG = "GameLayer"

@Composable
fun GameLayer(
    modifier: Modifier = Modifier,
    innerPadding: Dp,
    theme: Theme,
    squareSize: Dp,
    scoreState: MutableState<Int>,
    viewModel: MainViewModel,
    game: UnfinishedGame
) {
    val scope = rememberCoroutineScope()
    val controller = remember {
        SizeFourGameController(game = game, coroutineScope =  scope, scoreState =  scoreState, viewModel = viewModel)
    }
    val horizontalAnimator = controller.animator.horizontalAnimator(squareSize = squareSize)
    val verticalAnimator = controller.animator.verticalAnimator(squareSize = squareSize)
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
                        controller.moveController.moveRight()
                        Log.d(TAG, "GameLayer: Successfully executed GameController#moveRight()")
                    } catch (e: NullPointerException) {
                        Log.d(
                            TAG,
                            "GameLayer: Exception caught executing GameController#moveRight()"
                        )
                        Log.d(TAG, controller.gameState.toString())
                    }
                    Direction.UP -> try {
                        controller.moveController.moveUp()
                        Log.d(TAG, "GameLayer: Successfully executed GameController#moveUp()")
                    } catch (e: NullPointerException) {
                        Log.d(TAG, "GameLayer: Exception caught executing GameController#moveUp()")
                        Log.d(TAG, controller.gameState.toString())
                    }
                    Direction.DOWN -> try {
                        controller.moveController.moveDown()
                        Log.d(TAG, "GameLayer: Successfully executed GameController#moveDown()")
                    } catch (e: NullPointerException) {
                        Log.d(
                            TAG,
                            "GameLayer: Exception caught executing GameController#moveDown()"
                        )
                        Log.d(TAG, controller.gameState.toString())
                    }
                    Direction.LEFT -> try {
                        controller.moveController.moveLeft()
                        Log.d(TAG, "GameLayer: Successfully executed GameController#moveLeft()")
                    } catch (e: NullPointerException) {
                        Log.d(
                            TAG,
                            "GameLayer: Exception caught executing GameController#moveLeft()"
                        )
                        Log.d(TAG, controller.gameState.toString())
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
                                } else horizontalAnimator[0].value,
                        top = innerPadding +
                                if (controller.gameState[0].justCreated.value) {
                                    squareSize * controller.gameState[0].positionY.value
                                } else verticalAnimator[0].value,
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
                                } else horizontalAnimator[1].value,
                        top = innerPadding +
                                if (controller.gameState[1].justCreated.value) {
                                    squareSize * controller.gameState[1].positionY.value
                                } else verticalAnimator[1].value,
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
                                } else horizontalAnimator[2].value,
                        top = innerPadding +
                                if (controller.gameState[2].justCreated.value) {
                                    squareSize * controller.gameState[2].positionY.value
                                } else verticalAnimator[2].value,
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
                                } else horizontalAnimator[3].value,
                        top = innerPadding +
                                if (controller.gameState[3].justCreated.value) {
                                    squareSize * controller.gameState[3].positionY.value
                                } else verticalAnimator[3].value,
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
                                } else horizontalAnimator[4].value,
                        top = innerPadding +
                                if (controller.gameState[4].justCreated.value) {
                                    squareSize * controller.gameState[4].positionY.value
                                } else verticalAnimator[4].value,
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
                                } else horizontalAnimator[5].value,
                        top = innerPadding +
                                if (controller.gameState[5].justCreated.value) {
                                    squareSize * controller.gameState[5].positionY.value
                                } else verticalAnimator[5].value,
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
                                } else horizontalAnimator[6].value,
                        top = innerPadding +
                                if (controller.gameState[6].justCreated.value) {
                                    squareSize * controller.gameState[6].positionY.value
                                } else verticalAnimator[6].value,
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
                                } else horizontalAnimator[7].value,
                        top = innerPadding +
                                if (controller.gameState[7].justCreated.value) {
                                    squareSize * controller.gameState[7].positionY.value
                                } else verticalAnimator[7].value,
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
                                } else horizontalAnimator[8].value,
                        top = innerPadding +
                                if (controller.gameState[8].justCreated.value) {
                                    squareSize * controller.gameState[8].positionY.value
                                } else verticalAnimator[8].value,
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
                                } else horizontalAnimator[9].value,
                        top = innerPadding +
                                if (controller.gameState[9].justCreated.value) {
                                    squareSize * controller.gameState[9].positionY.value
                                } else verticalAnimator[9].value,
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
                                } else horizontalAnimator[10].value,
                        top = innerPadding +
                                if (controller.gameState[10].justCreated.value) {
                                    squareSize * controller.gameState[10].positionY.value
                                } else verticalAnimator[10].value,
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
                                } else horizontalAnimator[11].value,
                        top = innerPadding +
                                if (controller.gameState[11].justCreated.value) {
                                    squareSize * controller.gameState[11].positionY.value
                                } else verticalAnimator[11].value,
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
                                } else horizontalAnimator[12].value,
                        top = innerPadding +
                                if (controller.gameState[12].justCreated.value) {
                                    squareSize * controller.gameState[12].positionY.value
                                } else verticalAnimator[12].value,
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
                                } else horizontalAnimator[13].value,
                        top = innerPadding +
                                if (controller.gameState[13].justCreated.value) {
                                    squareSize * controller.gameState[13].positionY.value
                                } else verticalAnimator[13].value,
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
                                } else horizontalAnimator[14].value,
                        top = innerPadding +
                                if (controller.gameState[14].justCreated.value) {
                                    squareSize * controller.gameState[14].positionY.value
                                } else verticalAnimator[14].value,
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
                                } else horizontalAnimator[15].value,
                        top = innerPadding +
                                if (controller.gameState[15].justCreated.value) {
                                    squareSize * controller.gameState[15].positionY.value
                                } else verticalAnimator[15].value,
                    )
                    .size(squareSize - innerPadding * 2),
                styles = theme.tileStyles,
                level = controller.gameState[15].level.value!!
            )
        }
    }
}