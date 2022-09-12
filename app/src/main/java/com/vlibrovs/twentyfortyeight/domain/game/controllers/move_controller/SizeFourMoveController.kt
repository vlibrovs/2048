package com.vlibrovs.twentyfortyeight.domain.game.controllers.move_controller

import com.vlibrovs.twentyfortyeight.common.Constants
import com.vlibrovs.twentyfortyeight.domain.game.controllers.generator.Generator
import com.vlibrovs.twentyfortyeight.domain.game.controllers.scheme_controller.SchemeController
import com.vlibrovs.twentyfortyeight.domain.game.controllers.stats_controller.StatsController
import com.vlibrovs.twentyfortyeight.domain.game.model.game_state.GameState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SizeFourMoveController(
    private val gameState: GameState,
    private val schemeController: SchemeController,
    private val generator: Generator,
    private val statsController: StatsController,
    private val coroutineScope: CoroutineScope
) : MoveController(gameState, schemeController, generator, statsController, coroutineScope) {

    override fun moveRight() {
        for (tileData in gameState) tileData.justCreated.value = false
        var successful = false
        val scheme = schemeController.getRowScheme()
        for (row in scheme) {
            scheme[1]
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
                generator.generate()
                statsController.moves.value++
            }
        }
    }

    override fun moveLeft() {
        for (tileData in gameState) tileData.justCreated.value = false
        var successful = false
        val scheme = schemeController.getRowScheme()
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
                generator.generate()
                statsController.moves.value++
            }
        }
    }

    override fun moveDown() {
        for (tileData in gameState) tileData.justCreated.value = false
        var successful = false
        val scheme = schemeController.getColumnScheme()
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
                generator.generate()
                statsController.moves.value++
            }
        }
    }

    override fun moveUp() {
        for (tileData in gameState) tileData.justCreated.value = false
        val scheme = schemeController.getColumnScheme()
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
                generator.generate()
                statsController.moves.value++
            }
        }
    }
}