package com.vlibrovs.twentyfortyeight.domain.game

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Dp
import com.vlibrovs.twentyfortyeight.common.Constants
import kotlinx.coroutines.*
import kotlin.random.Random

class GameController(private val coroutineScope: CoroutineScope) {

    private val _score = mutableStateOf(0)
    val score
        get() = _score

    private val _gameEnded = mutableStateOf(false)
    val gameEnded
        get() = _gameEnded

    private val moves = mutableStateOf(0)

    val gameState = arrayOf(
        TileData(
            level = mutableStateOf(null),
            positionX = mutableStateOf(0),
            positionY = mutableStateOf(0)
        ),
        TileData(
            level = mutableStateOf(null),
            positionX = mutableStateOf(1),
            positionY = mutableStateOf(0)
        ),
        TileData(
            level = mutableStateOf(null),
            positionX = mutableStateOf(2),
            positionY = mutableStateOf(0)
        ),
        TileData(
            level = mutableStateOf(null),
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

    private fun getRowScheme(): Array<Array<TileData>> {
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

    private fun getColumnScheme(): Array<Array<TileData>> {
        val array: Array<Array<TileData?>> = Array(4) {
            Array(4) {
                null
            }
        }
        for (tileData in gameState) {
            array[tileData.positionX.value][tileData.positionY.value] = tileData
        }
        return array.map { row -> row.map { item -> item!! }.toTypedArray() }.toTypedArray()
    }

    fun moveRight() {
        for (tileData in gameState) tileData.justCreated.value = false
        var successful = false
        val scheme = getRowScheme()
        for (row in scheme) {
            when (String(CharArray(4) { index -> if (row[index].level.value == null) '0' else '1' })) {
                "1111" -> {
                    if (row[3].level.value == row[2].level.value && row[0].level.value == row[1].level.value) {
                        row[2].positionX.value++
                        row[1].positionX.value++
                        row[0].positionX.value += 2
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[0].level.value = null
                            row[1].level.value = row[1].level.value!! + 1
                            row[2].level.value = null
                            row[3].level.value = row[3].level.value!! + 1
                            row[0].positionX.value = 0
                            row[2].positionX.value = 1
                        }
                        successful = true
                    } else if (row[3].level.value == row[2].level.value) {
                        row[0].positionX.value++
                        row[1].positionX.value++
                        row[2].positionX.value++
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[3].level.value = row[3].level.value!! + 1
                            row[2].level.value = null
                            row[2].positionX.value = 0
                        }
                        successful = true
                    } else if (row[2].level.value == row[1].level.value) {
                        row[0].positionX.value++
                        row[1].positionX.value++
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[2].level.value = row[2].level.value!! + 1
                            row[1].level.value = null
                            row[1].positionX.value = 0
                        }
                        successful = true
                    } else if (row[0].level.value == row[1].level.value) {
                        row[0].positionX.value++
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[1].level.value = row[1].level.value!! + 1
                            row[0].level.value = null
                            row[0].positionX.value = 0

                        }
                        successful = true
                    } else continue
                }

                "1110" -> {
                    if (row[1].level.value == row[2].level.value) {
                        row[0].positionX.value += 2
                        row[1].positionX.value += 2
                        row[2].positionX.value++
                        row[3].positionX.value = 1
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[2].level.value = row[2].level.value!! + 1
                            row[1].level.value = null
                            row[1].positionX.value = 0
                        }
                        successful = true
                    } else if (row[0].level.value == row[1].level.value) {
                        row[0].positionX.value += 2
                        row[1].positionX.value++
                        row[2].positionX.value++
                        row[3].positionX.value = 1
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[1].level.value = row[1].level.value!! + 1
                            row[0].level.value = null
                            row[0].positionX.value = 0
                        }
                        successful = true
                    } else {
                        row[0].positionX.value++
                        row[1].positionX.value++
                        row[2].positionX.value++
                        row[3].positionX.value = 0
                        successful = true
                    }
                }
                "0111" -> {
                    if (row[2].level.value == row[3].level.value) {
                        row[1].positionX.value++
                        row[2].positionX.value++
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[3].level.value = row[3].level.value!! + 1
                            row[2].level.value = null
                            row[2].positionX.value = 1
                        }
                        successful = true
                    } else if (row[1].level.value == row[2].level.value) {
                        row[1].positionX.value++
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[2].level.value = row[2].level.value!! + 1
                            row[1].level.value = null
                            row[1].positionX.value = 1
                        }
                        successful = true
                    } else continue
                }
                "1011" -> {
                    if (row[2].level.value == row[3].level.value) {
                        row[0].positionX.value += 2
                        row[2].positionX.value++
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[3].level.value = row[3].level.value!! + 1
                            row[2].level.value = null
                            row[2].positionX.value = 0
                        }
                    } else if (row[0].level.value == row[2].level.value) {
                        row[0].positionX.value += 2
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[2].level.value = row[2].level.value!! + 1
                            row[0].level.value = null
                            row[0].positionX.value = 0
                        }
                    } else {
                        row[0].positionX.value++
                        row[1].positionX.value = 0
                    }
                    successful = true
                }
                "1101" -> {
                    successful = true
                    if (row[1].level.value == row[3].level.value) {
                        row[0].positionX.value += 2
                        row[1].positionX.value += 2
                        row[2].positionX.value = 1
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[3].level.value = row[3].level.value!! + 1
                            row[1].level.value = null
                            row[1].positionX.value = 0
                        }
                    } else if (row[0].level.value == row[1].level.value) {
                        row[1].positionX.value++
                        row[0].positionX.value += 2
                        row[2].positionX.value = 1
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[1].level.value = row[1].level.value!! + 1
                            row[0].level.value = null
                            row[0].positionX.value = 0
                        }
                    } else {
                        row[0].positionX.value++
                        row[1].positionX.value++
                        row[2].positionX.value = 0
                    }
                }

                "1100" -> {
                    successful = true
                    if (row[0].level.value == row[1].level.value) {
                        row[1].positionX.value += 2
                        row[0].positionX.value += 3
                        row[2].positionX.value = 0
                        row[3].positionX.value = 1
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[1].level.value = row[1].level.value!! + 1
                            row[0].level.value = null
                            row[0].positionX.value = 2
                        }
                    } else {
                        row[0].positionX.value += 2
                        row[1].positionX.value += 2
                        row[2].positionX.value = 0
                        row[3].positionX.value = 1
                    }
                }
                "1010" -> {
                    successful = true
                    if (row[0].level.value == row[2].level.value) {
                        row[2].positionX.value++
                        row[0].positionX.value += 3
                        row[3].positionX.value = 0
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[2].level.value = row[2].level.value!! + 1
                            row[0].level.value = null
                            row[0].positionX.value = 2
                        }
                    } else {
                        row[0].positionX.value += 2
                        row[2].positionX.value++
                        row[3].positionX.value = 0
                    }
                }
                "1001" -> {
                    successful = true
                    if (row[0].level.value == row[3].level.value) {
                        row[0].positionX.value += 3
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[3].level.value = row[3].level.value!! + 1
                            row[0].level.value = null
                            row[0].positionX.value = 0
                        }
                    } else {
                        row[0].positionX.value += 2
                        row[2].positionX.value -= 2
                    }
                }
                "0101" -> {
                    successful = true
                    if (row[1].level.value == row[3].level.value) {
                        row[1].positionX.value += 2
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[3].level.value = row[3].level.value!! + 1
                            row[1].level.value = null
                            row[1].positionX.value = 1
                        }
                    } else {
                        row[1].positionX.value++
                        row[2].positionX.value--
                    }
                }
                "0110" -> {
                    successful = true
                    if (row[1].level.value == row[2].level.value) {
                        row[2].positionX.value++
                        row[1].positionX.value += 2
                        row[3].positionX.value = 1
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[2].level.value = row[2].level.value!! + 1
                            row[1].level.value = null
                            row[1].positionX.value = 2
                        }
                    } else {
                        row[1].positionX.value++
                        row[2].positionX.value++
                        row[3].positionX.value = 1
                    }
                }
                "0011" -> {
                    if (row[2].level.value == row[3].level.value) {
                        row[2].positionX.value++
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[3].level.value = row[3].level.value!! + 1
                            row[2].level.value = null
                            row[2].positionX.value = 2
                        }
                        successful = true
                    } else continue
                }

                "1000" -> {
                    successful = true
                    row[0].positionX.value = 3
                    row[3].positionX.value = 0
                }
                "0100" -> {
                    successful = true
                    row[1].positionX.value = 3
                    row[3].positionX.value = 1
                }
                "0010" -> {
                    successful = true
                    row[2].positionX.value = 3
                    row[3].positionX.value = 2
                }
            }

        }
        if (successful) {
            coroutineScope.launch {
                delay(Constants.ANIMATION_DURATION.toLong())
                generateNext()
                moves.value++
            }
        }
    }

    fun moveLeft() {
        for (tileData in gameState) tileData.justCreated.value = false
        var successful = false
        val scheme = getRowScheme()
        for (row in scheme) {
            when (String(CharArray(4) { index -> if (row[index].level.value == null) '0' else '1' })) {
                "1111" -> {
                    if (row[0].level.value == row[1].level.value && row[2].level.value == row[3].level.value) {
                        row[1].positionX.value--
                        row[2].positionX.value--
                        row[3].positionX.value -= 2
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[3].level.value = null
                            row[2].level.value = row[2].level.value!! + 1
                            row[1].level.value = null
                            row[0].level.value = row[0].level.value!! + 1
                            row[3].positionX.value = 3
                            row[1].positionX.value = 2
                        }
                        successful = true
                    } else if (row[0].level.value == row[1].level.value) {
                        row[3].positionX.value--
                        row[2].positionX.value--
                        row[1].positionX.value--
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[0].level.value = row[0].level.value!! + 1
                            row[1].level.value = null
                            row[1].positionX.value = 3
                        }
                        successful = true
                    } else if (row[1].level.value == row[2].level.value) {
                        successful = true
                        row[3].positionX.value--
                        row[2].positionX.value--
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[1].level.value = row[1].level.value!! + 1
                            row[2].level.value = null
                            row[2].positionX.value = 3
                        }
                    } else if (row[3].level.value == row[2].level.value) {
                        successful = true
                        row[3].positionX.value--
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[2].level.value = row[2].level.value!! + 1
                            row[3].level.value = null
                            row[3].positionX.value = 3

                        }
                    } else continue
                }

                "1110" -> {
                    if (row[1].level.value == row[0].level.value) {
                        successful = true
                        row[2].positionX.value--
                        row[1].positionX.value--
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[0].level.value = row[0].level.value!! + 1
                            row[1].level.value = null
                            row[1].positionX.value = 2
                        }
                    } else if (row[2].level.value == row[1].level.value) {
                        successful = true
                        row[2].positionX.value--
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[1].level.value = row[1].level.value!! + 1
                            row[2].level.value = null
                            row[2].positionX.value = 2
                        }
                    } else continue
                }
                "0111" -> {
                    successful = true
                    if (row[2].level.value == row[1].level.value) {
                        row[3].positionX.value -= 2
                        row[2].positionX.value -= 2
                        row[1].positionX.value--
                        row[0].positionX.value = 2
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[1].level.value = row[1].level.value!! + 1
                            row[2].level.value = null
                            row[2].positionX.value = 3
                        }
                    } else if (row[3].level.value == row[2].level.value) {
                        row[3].positionX.value -= 2
                        row[2].positionX.value--
                        row[1].positionX.value--
                        row[0].positionX.value = 3
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[2].level.value = row[2].level.value!! + 1
                            row[3].level.value = null
                            row[3].positionX.value = 3
                        }
                    } else {
                        row[3].positionX.value--
                        row[2].positionX.value--
                        row[1].positionX.value--
                        row[0].positionX.value = 3
                    }
                }
                "1011" -> {
                    successful = true
                    if (row[2].level.value == row[0].level.value) {
                        row[3].positionX.value -= 2
                        row[2].positionX.value -= 2
                        row[1].positionX.value = 2
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[0].level.value = row[0].level.value!! + 1
                            row[2].level.value = null
                            row[2].positionX.value = 3
                        }
                    } else if (row[3].level.value == row[2].level.value) {
                        row[2].positionX.value--
                        row[3].positionX.value -= 2
                        row[1].positionX.value = 2
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[2].level.value = row[2].level.value!! + 1
                            row[3].level.value = null
                            row[3].positionX.value = 3
                        }
                    } else {
                        row[3].positionX.value--
                        row[2].positionX.value--
                        row[1].positionX.value = 3
                    }
                }
                "1101" -> {
                    successful = true
                    if (row[1].level.value == row[0].level.value) {
                        row[3].positionX.value -= 2
                        row[1].positionX.value--
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[0].level.value = row[0].level.value!! + 1
                            row[1].level.value = null
                            row[1].positionX.value = 3
                        }
                    } else if (row[3].level.value == row[1].level.value) {
                        row[3].positionX.value -= 2
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[1].level.value = row[1].level.value!! + 1
                            row[3].level.value = null
                            row[3].positionX.value = 3
                        }
                    } else {
                        row[3].positionX.value--
                        row[2].positionX.value = 3
                    }
                }

                "0011" -> {
                    successful = true
                    if (row[3].level.value == row[2].level.value) {
                        row[2].positionX.value -= 2
                        row[3].positionX.value -= 3
                        row[1].positionX.value = 3
                        row[0].positionX.value = 2
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[2].level.value = row[2].level.value!! + 1
                            row[3].level.value = null
                            row[3].positionX.value = 1
                        }
                    } else {
                        row[3].positionX.value -= 2
                        row[2].positionX.value -= 2
                        row[1].positionX.value = 3
                        row[0].positionX.value = 2
                    }
                }
                "1100" -> {
                    if (row[1].level.value == row[0].level.value) {
                        successful = true
                        row[1].positionX.value--
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[0].level.value = row[0].level.value!! + 1
                            row[1].level.value = null
                            row[1].positionX.value = 1
                        }
                    } else continue
                }
                "0101" -> {
                    successful = true
                    if (row[3].level.value == row[1].level.value) {
                        row[1].positionX.value--
                        row[3].positionX.value -= 3
                        row[0].positionX.value = 3
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[1].level.value = row[1].level.value!! + 1
                            row[3].level.value = null
                            row[3].positionX.value = 1
                        }
                    } else {
                        row[3].positionX.value -= 2
                        row[1].positionX.value--
                        row[0].positionX.value = 3
                    }
                }
                "1010" -> {
                    successful = true
                    if (row[2].level.value == row[0].level.value) {
                        row[2].positionX.value -= 2
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[0].level.value = row[0].level.value!! + 1
                            row[2].level.value = null
                            row[2].positionX.value = 2
                        }
                    } else {
                        row[2].positionX.value--
                        row[1].positionX.value++
                    }
                }
                "1001" -> {
                    successful = true
                    if (row[3].level.value == row[0].level.value) {
                        row[3].positionX.value = 0
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[0].level.value = row[0].level.value!! + 1
                            row[3].level.value = null
                            row[3].positionX.value = 3
                        }
                    } else {
                        row[3].positionX.value -= 2
                        row[1].positionX.value += 2
                    }
                }
                "0110" -> {
                    successful = true
                    if (row[2].level.value == row[1].level.value) {
                        row[1].positionX.value--
                        row[2].positionX.value -= 2
                        row[0].positionX.value = 2
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            row[1].level.value = row[1].level.value!! + 1
                            row[2].level.value = null
                            row[2].positionX.value = 1
                        }
                    } else {
                        row[2].positionX.value--
                        row[1].positionX.value--
                        row[0].positionX.value = 2
                    }
                }

                "0001" -> {
                    successful = true
                    row[3].positionX.value = 0
                    row[0].positionX.value = 3
                }
                "0010" -> {
                    successful = true
                    row[2].positionX.value = 0
                    row[0].positionX.value = 2
                }
                "0100" -> {
                    successful = true
                    row[1].positionX.value = 0
                    row[0].positionX.value = 1
                }
            }
        }
        if (successful) {
            coroutineScope.launch {
                delay(Constants.ANIMATION_DURATION.toLong())
                generateNext()
                moves.value++
            }
        }
    }

    fun moveDown() {
        for (tileData in gameState) tileData.justCreated.value = false
        var successful = false
        val scheme = getColumnScheme()
        for (column in scheme) {
            when (String(CharArray(4) { index -> if (column[index].level.value == null) '0' else '1' })) {
                "1111" -> {
                    if (column[3].level.value == column[2].level.value && column[0].level.value == column[1].level.value) {
                        successful = true
                        column[2].positionY.value++
                        column[1].positionY.value++
                        column[0].positionY.value += 2
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[0].level.value = null
                            column[1].level.value = column[1].level.value!! + 1
                            column[2].level.value = null
                            column[3].level.value = column[3].level.value!! + 1
                            column[0].positionY.value = 0
                            column[2].positionY.value = 1
                        }
                    } else if (column[3].level.value == column[2].level.value) {
                        successful = true
                        column[0].positionY.value++
                        column[1].positionY.value++
                        column[2].positionY.value++
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[3].level.value = column[3].level.value!! + 1
                            column[2].level.value = null
                            column[2].positionY.value = 0
                        }
                    } else if (column[2].level.value == column[1].level.value) {
                        successful = true
                        column[0].positionY.value++
                        column[1].positionY.value++
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[2].level.value = column[2].level.value!! + 1
                            column[1].level.value = null
                            column[1].positionY.value = 0
                        }
                    } else if (column[0].level.value == column[1].level.value) {
                        successful = true
                        column[0].positionY.value++
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[1].level.value = column[1].level.value!! + 1
                            column[0].level.value = null
                            column[0].positionY.value = 0

                        }
                    } else continue
                }

                "1110" -> {
                    successful = true
                    if (column[1].level.value == column[2].level.value) {
                        column[0].positionY.value += 2
                        column[1].positionY.value += 2
                        column[2].positionY.value++
                        column[3].positionY.value = 1
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[2].level.value = column[2].level.value!! + 1
                            column[1].level.value = null
                            column[1].positionY.value = 0
                        }
                    } else if (column[0].level.value == column[1].level.value) {
                        column[0].positionY.value += 2
                        column[1].positionY.value++
                        column[2].positionY.value++
                        column[3].positionY.value = 1
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[1].level.value = column[1].level.value!! + 1
                            column[0].level.value = null
                            column[0].positionY.value = 0
                        }
                    } else {
                        column[0].positionY.value++
                        column[1].positionY.value++
                        column[2].positionY.value++
                        column[3].positionY.value = 0
                    }
                }
                "0111" -> {
                    if (column[2].level.value == column[3].level.value) {
                        successful = true
                        column[1].positionY.value++
                        column[2].positionY.value++
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[3].level.value = column[3].level.value!! + 1
                            column[2].level.value = null
                            column[2].positionY.value = 1
                        }
                    } else if (column[1].level.value == column[2].level.value) {
                        successful = true
                        column[1].positionY.value++
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[2].level.value = column[2].level.value!! + 1
                            column[1].level.value = null
                            column[1].positionY.value = 1
                        }
                    } else continue
                }
                "1011" -> {
                    successful = true
                    if (column[2].level.value == column[3].level.value) {
                        column[0].positionY.value += 2
                        column[2].positionY.value++
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[3].level.value = column[3].level.value!! + 1
                            column[2].level.value = null
                            column[2].positionY.value = 0
                        }
                    } else if (column[0].level.value == column[2].level.value) {
                        column[0].positionY.value += 2
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[2].level.value = column[2].level.value!! + 1
                            column[0].level.value = null
                            column[0].positionY.value = 0
                        }
                    } else {
                        column[0].positionY.value++
                        column[1].positionY.value = 0
                    }
                }
                "1101" -> {
                    successful = true
                    if (column[1].level.value == column[3].level.value) {
                        column[0].positionY.value += 2
                        column[1].positionY.value += 2
                        column[2].positionY.value = 1
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[3].level.value = column[3].level.value!! + 1
                            column[1].level.value = null
                            column[1].positionY.value = 0
                        }
                    } else if (column[0].level.value == column[1].level.value) {
                        column[1].positionY.value++
                        column[0].positionY.value += 2
                        column[2].positionY.value = 1
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[1].level.value = column[1].level.value!! + 1
                            column[0].level.value = null
                            column[0].positionY.value = 0
                        }
                    } else {
                        column[0].positionY.value++
                        column[1].positionY.value++
                        column[2].positionY.value = 0
                    }
                }

                "1100" -> {
                    successful = true
                    if (column[0].level.value == column[1].level.value) {
                        column[1].positionY.value += 2
                        column[0].positionY.value += 3
                        column[2].positionY.value = 0
                        column[3].positionY.value = 1
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[1].level.value = column[1].level.value!! + 1
                            column[0].level.value = null
                            column[0].positionY.value = 2
                        }
                    } else {
                        column[0].positionY.value += 2
                        column[1].positionY.value += 2
                        column[2].positionY.value = 0
                        column[3].positionY.value = 1
                    }
                }
                "1010" -> {
                    successful = true
                    if (column[0].level.value == column[2].level.value) {
                        column[2].positionY.value++
                        column[0].positionY.value += 3
                        column[3].positionY.value = 0
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[2].level.value = column[2].level.value!! + 1
                            column[0].level.value = null
                            column[0].positionY.value = 2
                        }
                    } else {
                        column[0].positionY.value += 2
                        column[2].positionY.value++
                        column[3].positionY.value = 0
                    }
                }
                "1001" -> {
                    successful = true
                    if (column[0].level.value == column[3].level.value) {
                        column[0].positionY.value += 3
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[3].level.value = column[3].level.value!! + 1
                            column[0].level.value = null
                            column[0].positionY.value = 0
                        }
                    } else {
                        column[0].positionY.value += 2
                        column[2].positionY.value -= 2
                    }
                }
                "0101" -> {
                    successful = true
                    if (column[1].level.value == column[3].level.value) {
                        column[1].positionY.value += 2
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[3].level.value = column[3].level.value!! + 1
                            column[1].level.value = null
                            column[1].positionY.value = 1
                        }
                    } else {
                        column[1].positionY.value++
                        column[2].positionY.value--
                    }
                }
                "0110" -> {
                    successful = true
                    if (column[1].level.value == column[2].level.value) {
                        column[2].positionY.value++
                        column[1].positionY.value += 2
                        column[3].positionY.value = 1
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[2].level.value = column[2].level.value!! + 1
                            column[1].level.value = null
                            column[1].positionY.value = 2
                        }
                    } else {
                        column[1].positionY.value++
                        column[2].positionY.value++
                        column[3].positionY.value = 1
                    }
                }
                "0011" -> {
                    if (column[2].level.value == column[3].level.value) {
                        successful = true
                        column[2].positionY.value++
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[3].level.value = column[3].level.value!! + 1
                            column[2].level.value = null
                            column[2].positionY.value = 2
                        }
                    } else continue
                }

                "1000" -> {
                    successful = true
                    column[0].positionY.value = 3
                    column[3].positionY.value = 0
                }
                "0100" -> {
                    successful = true
                    column[1].positionY.value = 3
                    column[3].positionY.value = 1
                }
                "0010" -> {
                    successful = true
                    column[2].positionY.value = 3
                    column[3].positionY.value = 2
                }
            }
        }
        if (successful) {
            coroutineScope.launch {
                delay(Constants.ANIMATION_DURATION.toLong())
                generateNext()
                moves.value++
            }
        }
    }

    fun moveUp() {
        for (tileData in gameState) tileData.justCreated.value = false
        val scheme = getColumnScheme()
        var successful = false
        for (column in scheme) {
            when (String(CharArray(4) { index -> if (column[index].level.value == null) '0' else '1' })) {
                "1111" -> {
                    if (column[0].level.value == column[1].level.value && column[2].level.value == column[3].level.value) {
                        successful = true
                        column[1].positionY.value--
                        column[2].positionY.value--
                        column[3].positionY.value -= 2
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[3].level.value = null
                            column[2].level.value = column[2].level.value!! + 1
                            column[1].level.value = null
                            column[0].level.value = column[0].level.value!! + 1
                            column[3].positionY.value = 3
                            column[1].positionY.value = 2
                        }
                    } else if (column[0].level.value == column[1].level.value) {
                        successful = true
                        column[3].positionY.value--
                        column[2].positionY.value--
                        column[1].positionY.value--
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[0].level.value = column[0].level.value!! + 1
                            column[1].level.value = null
                            column[1].positionY.value = 3
                        }
                    } else if (column[1].level.value == column[2].level.value) {
                        successful = true
                        column[3].positionY.value--
                        column[2].positionY.value--
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[1].level.value = column[1].level.value!! + 1
                            column[2].level.value = null
                            column[2].positionY.value = 3
                        }
                    } else if (column[3].level.value == column[2].level.value) {
                        successful = true
                        column[3].positionY.value--
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[2].level.value = column[2].level.value!! + 1
                            column[3].level.value = null
                            column[3].positionY.value = 3

                        }
                    } else continue
                }

                "1110" -> {
                    if (column[1].level.value == column[0].level.value) {
                        successful = true
                        column[2].positionY.value--
                        column[1].positionY.value--
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[0].level.value = column[0].level.value!! + 1
                            column[1].level.value = null
                            column[1].positionY.value = 2
                        }
                    } else if (column[2].level.value == column[1].level.value) {
                        successful = true
                        column[2].positionY.value--
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[1].level.value = column[1].level.value!! + 1
                            column[2].level.value = null
                            column[2].positionY.value = 2
                        }
                    } else continue
                }
                "0111" -> {
                    successful = true
                    if (column[2].level.value == column[1].level.value) {
                        column[3].positionY.value -= 2
                        column[2].positionY.value -= 2
                        column[1].positionY.value--
                        column[0].positionY.value = 2
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[1].level.value = column[1].level.value!! + 1
                            column[2].level.value = null
                            column[2].positionY.value = 3
                        }
                    } else if (column[3].level.value == column[2].level.value) {
                        column[3].positionY.value -= 2
                        column[2].positionY.value--
                        column[1].positionY.value--
                        column[0].positionY.value = 3
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[2].level.value = column[2].level.value!! + 1
                            column[3].level.value = null
                            column[3].positionY.value = 3
                        }
                    } else {
                        column[3].positionY.value--
                        column[2].positionY.value--
                        column[1].positionY.value--
                        column[0].positionY.value = 3
                    }
                }
                "1011" -> {
                    successful = true
                    if (column[2].level.value == column[0].level.value) {
                        column[3].positionY.value -= 2
                        column[2].positionY.value -= 2
                        column[1].positionY.value = 2
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[0].level.value = column[0].level.value!! + 1
                            column[2].level.value = null
                            column[2].positionY.value = 3
                        }
                    } else if (column[3].level.value == column[2].level.value) {
                        column[2].positionY.value--
                        column[3].positionY.value -= 2
                        column[1].positionY.value = 2
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[2].level.value = column[2].level.value!! + 1
                            column[3].level.value = null
                            column[3].positionY.value = 3
                        }
                    } else {
                        column[3].positionY.value--
                        column[2].positionY.value--
                        column[1].positionY.value = 3
                    }
                }
                "1101" -> {
                    successful = true
                    if (column[1].level.value == column[0].level.value) {
                        column[3].positionY.value -= 2
                        column[1].positionY.value--
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[0].level.value = column[0].level.value!! + 1
                            column[1].level.value = null
                            column[1].positionY.value = 3
                        }
                    } else if (column[3].level.value == column[1].level.value) {
                        column[3].positionY.value -= 2
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[1].level.value = column[1].level.value!! + 1
                            column[3].level.value = null
                            column[3].positionY.value = 3
                        }
                    } else {
                        column[3].positionY.value--
                        column[2].positionY.value = 3
                    }
                }

                "0011" -> {
                    successful = true
                    if (column[3].level.value == column[2].level.value) {
                        column[2].positionY.value -= 2
                        column[3].positionY.value -= 3
                        column[1].positionY.value = 3
                        column[0].positionY.value = 2
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[2].level.value = column[2].level.value!! + 1
                            column[3].level.value = null
                            column[3].positionY.value = 1
                        }
                    } else {
                        column[3].positionY.value -= 2
                        column[2].positionY.value -= 2
                        column[1].positionY.value = 3
                        column[0].positionY.value = 2
                    }
                }
                "1100" -> {
                    if (column[1].level.value == column[0].level.value) {
                        successful = true
                        column[1].positionY.value--
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[0].level.value = column[0].level.value!! + 1
                            column[1].level.value = null
                            column[1].positionY.value = 1
                        }
                    } else continue
                }
                "0101" -> {
                    successful = true
                    if (column[3].level.value == column[1].level.value) {
                        column[1].positionY.value--
                        column[3].positionY.value -= 3
                        column[0].positionY.value = 3
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[1].level.value = column[1].level.value!! + 1
                            column[3].level.value = null
                            column[3].positionY.value = 1
                        }
                    } else {
                        column[3].positionY.value -= 2
                        column[1].positionY.value--
                        column[0].positionY.value = 3
                    }
                }
                "1010" -> {
                    successful = true
                    if (column[2].level.value == column[0].level.value) {
                        column[2].positionY.value -= 2
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[0].level.value = column[0].level.value!! + 1
                            column[2].level.value = null
                            column[2].positionY.value = 2
                        }
                    } else {
                        column[2].positionY.value--
                        column[1].positionY.value++
                    }
                }
                "1001" -> {
                    successful = true
                    if (column[3].level.value == column[0].level.value) {
                        column[3].positionY.value = 0
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[0].level.value = column[0].level.value!! + 1
                            column[3].level.value = null
                            column[3].positionY.value = 3
                        }
                    } else {
                        column[3].positionY.value -= 2
                        column[1].positionY.value += 2
                    }
                }
                "0110" -> {
                    successful = true
                    if (column[2].level.value == column[1].level.value) {
                        column[1].positionY.value--
                        column[2].positionY.value -= 2
                        column[0].positionY.value = 2
                        coroutineScope.launch {
                            delay(Constants.ANIMATION_DURATION.toLong())
                            column[1].level.value = column[1].level.value!! + 1
                            column[2].level.value = null
                            column[2].positionY.value = 1
                        }
                    } else {
                        column[2].positionY.value--
                        column[1].positionY.value--
                        column[0].positionY.value = 2
                    }
                }

                "0001" -> {
                    successful = true
                    column[3].positionY.value = 0
                    column[0].positionY.value = 3
                }
                "0010" -> {
                    successful = true
                    column[2].positionY.value = 0
                    column[0].positionY.value = 2
                }
                "0100" -> {
                    successful = true
                    column[1].positionY.value = 0
                    column[0].positionY.value = 1
                }
            }
        }
        if (successful) {
            coroutineScope.launch {
                delay(Constants.ANIMATION_DURATION.toLong())
                generateNext()
                moves.value++
            }
        }
    }

    fun generateNext() {
        val emptySquaresList = mutableListOf<TileData>()
        for (tileData in gameState) {
            if (tileData.level.value == null) emptySquaresList.add(tileData)
        }
        if (emptySquaresList.size == 0) {
            _gameEnded.value = true
            return
        }
        val position = Random.nextInt(emptySquaresList.size)
        val level = if (Random.nextInt(9) == 0) 2 else 1
        emptySquaresList[position].apply {
            justCreated.value = true
            this.level.value = level
        }
    }

    init {
        generateNext()
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