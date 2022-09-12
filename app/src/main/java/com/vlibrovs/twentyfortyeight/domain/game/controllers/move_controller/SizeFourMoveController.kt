package com.vlibrovs.twentyfortyeight.domain.game.controllers.move_controller

import com.vlibrovs.twentyfortyeight.common.Constants
import com.vlibrovs.twentyfortyeight.domain.game.controllers.generator.Generator
import com.vlibrovs.twentyfortyeight.domain.game.controllers.scheme_controller.SchemeController
import com.vlibrovs.twentyfortyeight.domain.game.controllers.stats_controller.StatsController
import com.vlibrovs.twentyfortyeight.domain.game.model.TileData
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
                    if (row.tilesLevelEqual(2, 3) && row.tilesLevelEqual(0, 1)) {
                        row[2].moveRight()
                        row[1].moveRight()
                        row[0].moveRight(2)
                        afterAnimation {
                            row[0].hide()
                            row[1].increaseLevel()
                            row[2].hide()
                            row[3].increaseLevel()
                            row[0].setX(0)
                            row[2].setX(1)
                            statsController.addScoreByLevel(row[1].level.value!!)
                            statsController.addScoreByLevel(row[3].level.value!!)
                        }
                        successful = true
                    } else if (row.tilesLevelEqual(2, 3)) {
                        row[0].moveRight()
                        row[1].moveRight()
                        row[2].moveRight()
                        afterAnimation {
                            row[3].increaseLevel()
                            row[2].hide()
                            row[2].setX(0)
                            statsController.addScoreByLevel(row[3].level.value!!)
                        }
                        successful = true
                    } else if (row.tilesLevelEqual(1, 2)) {
                        row[0].moveRight()
                        row[1].moveRight()
                        afterAnimation {
                            row[2].increaseLevel()
                            row[1].hide()
                            row[1].setX(0)
                            statsController.addScoreByLevel(row[2].level.value!!)
                        }
                        successful = true
                    } else if (row.tilesLevelEqual(0, 1)) {
                        row[0].moveRight()
                        afterAnimation {
                            row[1].increaseLevel()
                            row[0].hide()
                            row[0].setX(0)
                            statsController.addScoreByLevel(row[1].level.value!!)
                        }
                        successful = true
                    } else continue
                }

                "1110" -> {
                    if (row.tilesLevelEqual(1, 2)) {
                        row[0].moveRight(2)
                        row[1].moveRight(2)
                        row[2].moveRight()
                        row[3].setX(1)
                        afterAnimation {
                            row[2].increaseLevel()
                            row[1].hide()
                            row[1].setX(0)
                            statsController.addScoreByLevel(row[2].level.value!!)
                        }
                        successful = true
                    } else if (row.tilesLevelEqual(0, 1)) {
                        row[0].moveRight(2)
                        row[1].moveRight()
                        row[2].moveRight()
                        row[3].setX(1)
                        afterAnimation {
                            row[1].increaseLevel()
                            row[0].hide()
                            row[0].setX(0)
                            statsController.addScoreByLevel(row[1].level.value!!)
                        }
                        successful = true
                    } else {
                        row[0].moveRight()
                        row[1].moveRight()
                        row[2].moveRight()
                        row[3].setX(0)
                        successful = true
                    }
                }
                "0111" -> {
                    if (row.tilesLevelEqual(2, 3)) {
                        row[1].moveRight()
                        row[2].moveRight()
                        afterAnimation {
                            row[3].increaseLevel()
                            row[2].hide()
                            row[2].setX(1)
                            statsController.addScoreByLevel(row[3].level.value!!)
                        }
                        successful = true
                    } else if (row.tilesLevelEqual(1, 2)) {
                        row[1].moveRight()
                        afterAnimation {
                            row[2].increaseLevel()
                            row[1].hide()
                            row[1].setX(1)
                            statsController.addScoreByLevel(row[2].level.value!!)
                        }
                        successful = true
                    } else continue
                }
                "1011" -> {
                    if (row.tilesLevelEqual(2, 3)) {
                        row[0].moveRight(2)
                        row[2].moveRight()
                        afterAnimation {
                            row[3].increaseLevel()
                            row[2].hide()
                            row[2].setX(0)
                            statsController.addScoreByLevel(row[3].level.value!!)
                        }
                    } else if (row.tilesLevelEqual(0, 2)) {
                        row[0].moveRight(2)
                        afterAnimation {
                            row[2].increaseLevel()
                            row[0].hide()
                            row[0].setX(0)
                            statsController.addScoreByLevel(row[2].level.value!!)
                        }
                    } else {
                        row[0].moveRight()
                        row[1].setX(0)
                    }
                    successful = true
                }
                "1101" -> {
                    successful = true
                    if (row.tilesLevelEqual(1, 3)) {
                        row[0].moveRight(2)
                        row[1].moveRight(2)
                        row[2].setX(1)
                        afterAnimation {
                            row[3].increaseLevel()
                            row[1].hide()
                            row[1].setX(0)
                            statsController.addScoreByLevel(row[3].level.value!!)
                        }
                    } else if (row.tilesLevelEqual(0, 1)) {
                        row[1].moveRight()
                        row[0].moveRight(2)
                        row[2].setX(1)
                        afterAnimation {
                            row[1].increaseLevel()
                            row[0].hide()
                            row[0].setX(0)
                            statsController.addScoreByLevel(row[1].level.value!!)
                        }
                    } else {
                        row[0].moveRight()
                        row[1].moveRight()
                        row[2].setX(0)
                    }
                }

                "1100" -> {
                    successful = true
                    if (row.tilesLevelEqual(0, 1)) {
                        row[1].moveRight(2)
                        row[0].moveRight(3)
                        row[2].setX(0)
                        row[3].setX(1)
                        afterAnimation {
                            row[1].increaseLevel()
                            row[0].hide()
                            row[0].setX(2)
                            statsController.addScoreByLevel(row[1].level.value!!)
                        }
                    } else {
                        row[0].moveRight(2)
                        row[1].moveRight(2)
                        row[2].setX(0)
                        row[3].setX(1)
                    }
                }
                "1010" -> {
                    successful = true
                    if (row.tilesLevelEqual(0, 2)) {
                        row[2].moveRight()
                        row[0].moveRight(3)
                        row[3].setX(0)
                        afterAnimation {
                            row[2].increaseLevel()
                            row[0].hide()
                            row[0].setX(2)
                            statsController.addScoreByLevel(row[2].level.value!!)
                        }
                    } else {
                        row[0].moveRight(2)
                        row[2].moveRight()
                        row[3].setX(0)
                    }
                }
                "1001" -> {
                    successful = true
                    if (row.tilesLevelEqual(0, 3)) {
                        row[0].moveRight(3)
                        afterAnimation {
                            row[3].increaseLevel()
                            row[0].hide()
                            row[0].setX(0)
                            statsController.addScoreByLevel(row[3].level.value!!)
                        }
                    } else {
                        row[0].moveRight(2)
                        row[2].moveLeft(2)
                    }
                }
                "0101" -> {
                    successful = true
                    if (row.tilesLevelEqual(1, 3)) {
                        row[1].moveRight(2)
                        afterAnimation {
                            row[3].increaseLevel()
                            row[1].hide()
                            row[1].setX(1)
                            statsController.addScoreByLevel(row[3].level.value!!)
                        }
                    } else {
                        row[1].moveRight()
                        row[2].moveLeft()
                    }
                }
                "0110" -> {
                    successful = true
                    if (row.tilesLevelEqual(1, 2)) {
                        row[2].moveRight()
                        row[1].moveRight(2)
                        row[3].setX(1)
                        afterAnimation {
                            row[2].increaseLevel()
                            row[1].hide()
                            row[1].setX(2)
                            statsController.addScoreByLevel(row[2].level.value!!)
                        }
                    } else {
                        row[1].moveRight()
                        row[2].moveRight()
                        row[3].setX(1)
                    }
                }
                "0011" -> {
                    if (row.tilesLevelEqual(2, 3)) {
                        row[2].moveRight()
                        afterAnimation {
                            row[3].increaseLevel()
                            row[2].hide()
                            row[2].setX(2)
                            statsController.addScoreByLevel(row[3].level.value!!)
                        }
                        successful = true
                    } else continue
                }

                "1000" -> {
                    successful = true
                    row[0].setX(3)
                    row[3].setX(0)
                }
                "0100" -> {
                    successful = true
                    row[1].setX(3)
                    row[3].setX(1)
                }
                "0010" -> {
                    successful = true
                    row[2].setX(3)
                    row[3].setX(2)
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
                    if (row.tilesLevelEqual(0, 1) && row.tilesLevelEqual(2, 3)) {
                        row[1].moveLeft()
                        row[2].moveLeft()
                        row[3].moveLeft(2)
                        afterAnimation {
                            row[3].hide()
                            row[2].increaseLevel()
                            row[1].hide()
                            row[0].increaseLevel()
                            row[3].setX(3)
                            row[1].setX(2)
                            statsController.addScoreByLevel(row[2].level.value!!)
                            statsController.addScoreByLevel(row[0].level.value!!)
                        }
                        successful = true
                    } else if (row.tilesLevelEqual(0, 1)) {
                        row[3].moveLeft()
                        row[2].moveLeft()
                        row[1].moveLeft()
                        afterAnimation {
                            row[0].increaseLevel()
                            row[1].hide()
                            row[1].setX(3)
                            statsController.addScoreByLevel(row[0].level.value!!)
                        }
                        successful = true
                    } else if (row.tilesLevelEqual(1, 2)) {
                        successful = true
                        row[3].moveLeft()
                        row[2].moveLeft()
                        afterAnimation {
                            row[1].increaseLevel()
                            row[2].hide()
                            row[2].setX(3)
                            statsController.addScoreByLevel(row[1].level.value!!)
                        }
                    } else if (row.tilesLevelEqual(2, 3)) {
                        successful = true
                        row[3].moveLeft()
                        afterAnimation {
                            row[2].increaseLevel()
                            row[3].hide()
                            row[3].setX(3)
                            statsController.addScoreByLevel(row[2].level.value!!)
                        }
                    } else continue
                }

                "1110" -> {
                    if (row.tilesLevelEqual(0, 1)) {
                        successful = true
                        row[2].moveLeft()
                        row[1].moveLeft()
                        afterAnimation {
                            row[0].increaseLevel()
                            row[1].hide()
                            row[1].setX(2)
                            statsController.addScoreByLevel(row[0].level.value!!)
                        }
                    } else if (row.tilesLevelEqual(1, 2)) {
                        successful = true
                        row[2].moveLeft()
                        afterAnimation {
                            row[1].increaseLevel()
                            row[2].hide()
                            row[2].setX(2)
                            statsController.addScoreByLevel(row[1].level.value!!)
                        }
                    } else continue
                }
                "0111" -> {
                    successful = true
                    if (row.tilesLevelEqual(1, 2)) {
                        row[3].moveLeft(2)
                        row[2].moveLeft(2)
                        row[1].moveLeft()
                        row[0].setX(2)
                        afterAnimation {
                            row[1].increaseLevel()
                            row[2].hide()
                            row[2].setX(3)
                            statsController.addScoreByLevel(row[1].level.value!!)
                        }
                    } else if (row.tilesLevelEqual(2, 3)) {
                        row[3].moveLeft(2)
                        row[2].moveLeft()
                        row[1].moveLeft()
                        row[0].setX(3)
                        afterAnimation {
                            row[2].increaseLevel()
                            row[3].hide()
                            row[3].setX(3)
                            statsController.addScoreByLevel(row[2].level.value!!)
                        }
                    } else {
                        row[3].moveLeft()
                        row[2].moveLeft()
                        row[1].moveLeft()
                        row[0].setX(3)
                    }
                }
                "1011" -> {
                    successful = true
                    if (row.tilesLevelEqual(0, 2)) {
                        row[3].moveLeft(2)
                        row[2].moveLeft(2)
                        row[1].setX(2)
                        afterAnimation {
                            row[0].increaseLevel()
                            row[2].hide()
                            row[2].setX(3)
                            statsController.addScoreByLevel(row[0].level.value!!)
                        }
                    } else if (row.tilesLevelEqual(2, 3)) {
                        row[2].moveLeft()
                        row[3].moveLeft(2)
                        row[1].setX(2)
                        afterAnimation {
                            row[2].increaseLevel()
                            row[3].hide()
                            row[3].setX(3)
                            statsController.addScoreByLevel(row[2].level.value!!)
                        }
                    } else {
                        row[3].moveLeft()
                        row[2].moveLeft()
                        row[1].setX(3)
                    }
                }
                "1101" -> {
                    successful = true
                    if (row.tilesLevelEqual(0, 1)) {
                        row[3].moveLeft(2)
                        row[1].moveLeft()
                        afterAnimation {
                            row[0].increaseLevel()
                            row[1].hide()
                            row[1].setX(3)
                            statsController.addScoreByLevel(row[0].level.value!!)
                        }
                    } else if (row.tilesLevelEqual(1, 3)) {
                        row[3].moveLeft(2)
                        afterAnimation {
                            row[1].increaseLevel()
                            row[3].hide()
                            row[3].setX(3)
                            statsController.addScoreByLevel(row[1].level.value!!)
                        }
                    } else {
                        row[3].moveLeft()
                        row[2].setX(3)
                    }
                }

                "0011" -> {
                    successful = true
                    if (row.tilesLevelEqual(2, 3)) {
                        row[2].moveLeft(2)
                        row[3].moveLeft(3)
                        row[1].setX(3)
                        row[0].setX(2)
                        afterAnimation {
                            row[2].increaseLevel()
                            row[3].hide()
                            row[3].setX(1)
                            statsController.addScoreByLevel(row[2].level.value!!)
                        }
                    } else {
                        row[3].moveLeft(2)
                        row[2].moveLeft(2)
                        row[1].setX(3)
                        row[0].setX(2)
                    }
                }
                "1100" -> {
                    if (row.tilesLevelEqual(0, 1)) {
                        successful = true
                        row[1].moveLeft()
                        afterAnimation {
                            row[0].increaseLevel()
                            row[1].hide()
                            row[1].setX(1)
                            statsController.addScoreByLevel(row[0].level.value!!)
                        }
                    } else continue
                }
                "0101" -> {
                    successful = true
                    if (row.tilesLevelEqual(1, 3)) {
                        row[1].moveLeft()
                        row[3].moveLeft(3)
                        row[0].setX(3)
                        afterAnimation {
                            row[1].increaseLevel()
                            row[3].hide()
                            row[3].setX(1)
                            statsController.addScoreByLevel(row[1].level.value!!)
                        }
                    } else {
                        row[3].moveLeft(2)
                        row[1].moveLeft()
                        row[0].setX(3)
                    }
                }
                "1010" -> {
                    successful = true
                    if (row.tilesLevelEqual(0, 2)) {
                        row[2].moveLeft(2)
                        afterAnimation {
                            row[0].increaseLevel()
                            row[2].hide()
                            row[2].setX(2)
                            statsController.addScoreByLevel(row[0].level.value!!)
                        }
                    } else {
                        row[2].moveLeft()
                        row[1].moveRight()
                    }
                }
                "1001" -> {
                    successful = true
                    if (row.tilesLevelEqual(0, 3)) {
                        row[3].setX(0)
                        afterAnimation {
                            row[0].increaseLevel()
                            row[3].hide()
                            row[3].setX(3)
                            statsController.addScoreByLevel(row[0].level.value!!)
                        }
                    } else {
                        row[3].moveLeft(2)
                        row[1].moveRight(2)
                    }
                }
                "0110" -> {
                    successful = true
                    if (row.tilesLevelEqual(1, 2)) {
                        row[1].moveLeft()
                        row[2].moveLeft(2)
                        row[0].setX(2)
                        afterAnimation {
                            row[1].increaseLevel()
                            row[2].hide()
                            row[2].setX(1)
                            statsController.addScoreByLevel(row[1].level.value!!)
                        }
                    } else {
                        row[2].moveLeft()
                        row[1].moveLeft()
                        row[0].setX(2)
                    }
                }

                "0001" -> {
                    successful = true
                    row[3].setX(0)
                    row[0].setX(3)
                }
                "0010" -> {
                    successful = true
                    row[2].setX(0)
                    row[0].setX(2)
                }
                "0100" -> {
                    successful = true
                    row[1].setX(0)
                    row[0].setX(1)
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
                    if (column.tilesLevelEqual(2, 3) && column.tilesLevelEqual(0, 1)) {
                        successful = true
                        column[2].moveDown()
                        column[1].moveDown()
                        column[0].moveDown(2)
                        afterAnimation {
                            column[0].hide()
                            column[1].level.value = column[1].level.value!! + 1
                            column[2].hide()
                            column[3].level.value = column[3].level.value!! + 1
                            column[0].setY(0)
                            column[2].setY(1)
                            statsController.addScoreByLevel(column[3].level.value!!)
                            statsController.addScoreByLevel(column[1].level.value!!)
                        }
                    } else if (column.tilesLevelEqual(2, 3)) {
                        successful = true
                        column[0].moveDown()
                        column[1].moveDown()
                        column[2].moveDown()
                        afterAnimation {
                            column[3].level.value = column[3].level.value!! + 1
                            column[2].hide()
                            column[2].setY(0)
                            statsController.addScoreByLevel(column[3].level.value!!)
                        }
                    } else if (column.tilesLevelEqual(1, 2)) {
                        successful = true
                        column[0].moveDown()
                        column[1].moveDown()
                        afterAnimation {
                            column[2].level.value = column[2].level.value!! + 1
                            column[1].hide()
                            column[1].setY(0)
                            statsController.addScoreByLevel(column[2].level.value!!)
                        }
                    } else if (column.tilesLevelEqual(0, 1)) {
                        successful = true
                        column[0].moveDown()
                        afterAnimation {
                            column[1].level.value = column[1].level.value!! + 1
                            column[0].hide()
                            column[0].setY(0)
                            statsController.addScoreByLevel(column[1].level.value!!)
                        }
                    } else continue
                }

                "1110" -> {
                    successful = true
                    if (column.tilesLevelEqual(1, 2)) {
                        column[0].moveDown(2)
                        column[1].moveDown(2)
                        column[2].moveDown()
                        column[3].setY(1)
                        afterAnimation {
                            column[2].level.value = column[2].level.value!! + 1
                            column[1].hide()
                            column[1].setY(0)
                            statsController.addScoreByLevel(column[2].level.value!!)
                        }
                    } else if (column.tilesLevelEqual(0, 1)) {
                        column[0].moveDown(2)
                        column[1].moveDown()
                        column[2].moveDown()
                        column[3].setY(1)
                        afterAnimation {
                            column[1].level.value = column[1].level.value!! + 1
                            column[0].hide()
                            column[0].setY(0)
                            statsController.addScoreByLevel(column[1].level.value!!)
                        }
                    } else {
                        column[0].moveDown()
                        column[1].moveDown()
                        column[2].moveDown()
                        column[3].setY(0)
                    }
                }
                "0111" -> {
                    if (column.tilesLevelEqual(2, 3)) {
                        successful = true
                        column[1].moveDown()
                        column[2].moveDown()
                        afterAnimation {
                            column[3].level.value = column[3].level.value!! + 1
                            column[2].hide()
                            column[2].setY(1)
                            statsController.addScoreByLevel(column[3].level.value!!)
                        }
                    } else if (column.tilesLevelEqual(1, 2)) {
                        successful = true
                        column[1].moveDown()
                        afterAnimation {
                            column[2].level.value = column[2].level.value!! + 1
                            column[1].hide()
                            column[1].setY(1)
                            statsController.addScoreByLevel(column[2].level.value!!)
                        }
                    } else continue
                }
                "1011" -> {
                    successful = true
                    if (column.tilesLevelEqual(2, 3)) {
                        column[0].moveDown(2)
                        column[2].moveDown()
                        afterAnimation {
                            column[3].level.value = column[3].level.value!! + 1
                            column[2].hide()
                            column[2].setY(0)
                            statsController.addScoreByLevel(column[3].level.value!!)
                        }
                    } else if (column.tilesLevelEqual(0, 2)) {
                        column[0].moveDown(2)
                        afterAnimation {
                            column[2].level.value = column[2].level.value!! + 1
                            column[0].hide()
                            column[0].setY(0)
                            statsController.addScoreByLevel(column[2].level.value!!)
                        }
                    } else {
                        column[0].moveDown()
                        column[1].setY(0)
                    }
                }
                "1101" -> {
                    successful = true
                    if (column.tilesLevelEqual(1, 3)) {
                        column[0].moveDown(2)
                        column[1].moveDown(2)
                        column[2].setY(1)
                        afterAnimation {
                            column[3].level.value = column[3].level.value!! + 1
                            column[1].hide()
                            column[1].setY(0)
                            statsController.addScoreByLevel(column[3].level.value!!)
                        }
                    } else if (column.tilesLevelEqual(0, 1)) {
                        column[1].moveDown()
                        column[0].moveDown(2)
                        column[2].setY(1)
                        afterAnimation {
                            column[1].level.value = column[1].level.value!! + 1
                            column[0].hide()
                            column[0].setY(0)
                            statsController.addScoreByLevel(column[1].level.value!!)
                        }
                    } else {
                        column[0].moveDown()
                        column[1].moveDown()
                        column[2].setY(0)
                    }
                }

                "1100" -> {
                    successful = true
                    if (column.tilesLevelEqual(0, 1)) {
                        column[1].moveDown(2)
                        column[0].moveDown(3)
                        column[2].setY(0)
                        column[3].setY(1)
                        afterAnimation {
                            column[1].level.value = column[1].level.value!! + 1
                            column[0].hide()
                            column[0].setY(2)
                            statsController.addScoreByLevel(column[1].level.value!!)
                        }
                    } else {
                        column[0].moveDown(2)
                        column[1].moveDown(2)
                        column[2].setY(0)
                        column[3].setY(1)
                    }
                }
                "1010" -> {
                    successful = true
                    if (column.tilesLevelEqual(0, 2)) {
                        column[2].moveDown()
                        column[0].moveDown(3)
                        column[3].setY(0)
                        afterAnimation {
                            column[2].level.value = column[2].level.value!! + 1
                            column[0].hide()
                            column[0].setY(2)
                            statsController.addScoreByLevel(column[2].level.value!!)
                        }
                    } else {
                        column[0].moveDown(2)
                        column[2].moveDown()
                        column[3].setY(0)
                    }
                }
                "1001" -> {
                    successful = true
                    if (column.tilesLevelEqual(0, 3)) {
                        column[0].moveDown(3)
                        afterAnimation {
                            column[3].level.value = column[3].level.value!! + 1
                            column[0].hide()
                            column[0].setY(0)
                            statsController.addScoreByLevel(column[3].level.value!!)
                        }
                    } else {
                        column[0].moveDown(2)
                        column[2].moveUp(2)
                    }
                }
                "0101" -> {
                    successful = true
                    if (column.tilesLevelEqual(1, 3)) {
                        column[1].moveDown(2)
                        afterAnimation {
                            column[3].level.value = column[3].level.value!! + 1
                            column[1].hide()
                            column[1].setY(1)
                            statsController.addScoreByLevel(column[3].level.value!!)
                        }
                    } else {
                        column[1].moveDown()
                        column[2].moveUp()
                    }
                }
                "0110" -> {
                    successful = true
                    if (column.tilesLevelEqual(1, 2)) {
                        column[2].moveDown()
                        column[1].moveDown(2)
                        column[3].setY(1)
                        afterAnimation {
                            column[2].level.value = column[2].level.value!! + 1
                            column[1].hide()
                            column[1].setY(2)
                            statsController.addScoreByLevel(column[2].level.value!!)
                        }
                    } else {
                        column[1].moveDown()
                        column[2].moveDown()
                        column[3].setY(1)
                    }
                }
                "0011" -> {
                    if (column.tilesLevelEqual(2, 3)) {
                        successful = true
                        column[2].moveDown()
                        afterAnimation {
                            column[3].level.value = column[3].level.value!! + 1
                            column[2].hide()
                            column[2].setY(2)
                            statsController.addScoreByLevel(column[3].level.value!!)
                        }
                    } else continue
                }

                "1000" -> {
                    successful = true
                    column[0].setY(3)
                    column[3].setY(0)
                }
                "0100" -> {
                    successful = true
                    column[1].setY(3)
                    column[3].setY(1)
                }
                "0010" -> {
                    successful = true
                    column[2].setY(3)
                    column[3].setY(2)
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
                    if (column.tilesLevelEqual(0, 1) && column.tilesLevelEqual(2, 3)) {
                        successful = true
                        column[1].moveUp()
                        column[2].moveUp()
                        column[3].moveUp(2)
                        afterAnimation {
                            column[3].hide()
                            column[2].level.value = column[2].level.value!! + 1
                            column[1].hide()
                            column[0].level.value = column[0].level.value!! + 1
                            column[3].setY(3)
                            column[1].setY(2)
                            statsController.addScoreByLevel(column[0].level.value!!)
                            statsController.addScoreByLevel(column[2].level.value!!)
                        }
                    } else if (column.tilesLevelEqual(0, 1)) {
                        successful = true
                        column[3].moveUp()
                        column[2].moveUp()
                        column[1].moveUp()
                        afterAnimation {
                            column[0].level.value = column[0].level.value!! + 1
                            column[1].hide()
                            column[1].setY(3)
                            statsController.addScoreByLevel(column[0].level.value!!)
                        }
                    } else if (column.tilesLevelEqual(1, 2)) {
                        successful = true
                        column[3].moveUp()
                        column[2].moveUp()
                        afterAnimation {
                            column[1].level.value = column[1].level.value!! + 1
                            column[2].hide()
                            column[2].setY(3)
                            statsController.addScoreByLevel(column[1].level.value!!)
                        }
                    } else if (column.tilesLevelEqual(2, 3)) {
                        successful = true
                        column[3].moveUp()
                        afterAnimation {
                            column[2].level.value = column[2].level.value!! + 1
                            column[3].hide()
                            column[3].setY(3)
                            statsController.addScoreByLevel(column[2].level.value!!)

                        }
                    } else continue
                }

                "1110" -> {
                    if (column.tilesLevelEqual(0, 1)) {
                        successful = true
                        column[2].moveUp()
                        column[1].moveUp()
                        afterAnimation {
                            column[0].level.value = column[0].level.value!! + 1
                            column[1].hide()
                            column[1].setY(2)
                            statsController.addScoreByLevel(column[0].level.value!!)
                        }
                    } else if (column.tilesLevelEqual(1, 2)) {
                        successful = true
                        column[2].moveUp()
                        afterAnimation {
                            column[1].level.value = column[1].level.value!! + 1
                            column[2].hide()
                            column[2].setY(2)
                            statsController.addScoreByLevel(column[1].level.value!!)
                        }
                    } else continue
                }
                "0111" -> {
                    successful = true
                    if (column.tilesLevelEqual(1, 2)) {
                        column[3].moveUp(2)
                        column[2].moveUp(2)
                        column[1].moveUp()
                        column[0].setY(2)
                        afterAnimation {
                            column[1].level.value = column[1].level.value!! + 1
                            column[2].hide()
                            column[2].setY(3)
                            statsController.addScoreByLevel(column[1].level.value!!)
                        }
                    } else if (column.tilesLevelEqual(2, 3)) {
                        column[3].moveUp(2)
                        column[2].moveUp()
                        column[1].moveUp()
                        column[0].setY(3)
                        afterAnimation {
                            column[2].level.value = column[2].level.value!! + 1
                            column[3].hide()
                            column[3].setY(3)
                            statsController.addScoreByLevel(column[2].level.value!!)
                        }
                    } else {
                        column[3].moveUp()
                        column[2].moveUp()
                        column[1].moveUp()
                        column[0].setY(3)
                    }
                }
                "1011" -> {
                    successful = true
                    if (column.tilesLevelEqual(0, 2)) {
                        column[3].moveUp(2)
                        column[2].moveUp(2)
                        column[1].setY(2)
                        afterAnimation {
                            column[0].level.value = column[0].level.value!! + 1
                            column[2].hide()
                            column[2].setY(3)
                            statsController.addScoreByLevel(column[0].level.value!!)
                        }
                    } else if (column.tilesLevelEqual(2, 3)) {
                        column[2].moveUp()
                        column[3].moveUp(2)
                        column[1].setY(2)
                        afterAnimation {
                            column[2].level.value = column[2].level.value!! + 1
                            column[3].hide()
                            column[3].setY(3)
                            statsController.addScoreByLevel(column[2].level.value!!)
                        }
                    } else {
                        column[3].moveUp()
                        column[2].moveUp()
                        column[1].setY(3)
                    }
                }
                "1101" -> {
                    successful = true
                    if (column.tilesLevelEqual(0, 1)) {
                        column[3].moveUp(2)
                        column[1].moveUp()
                        afterAnimation {
                            column[0].level.value = column[0].level.value!! + 1
                            column[1].hide()
                            column[1].setY(3)
                            statsController.addScoreByLevel(column[0].level.value!!)
                        }
                    } else if (column.tilesLevelEqual(1, 3)) {
                        column[3].moveUp(2)
                        afterAnimation {
                            column[1].level.value = column[1].level.value!! + 1
                            column[3].hide()
                            column[3].setY(3)
                            statsController.addScoreByLevel(column[1].level.value!!)
                        }
                    } else {
                        column[3].moveUp()
                        column[2].setY(3)
                    }
                }

                "0011" -> {
                    successful = true
                    if (column.tilesLevelEqual(2, 3)) {
                        column[2].moveUp(2)
                        column[3].moveUp(3)
                        column[1].setY(3)
                        column[0].setY(2)
                        afterAnimation {
                            column[2].level.value = column[2].level.value!! + 1
                            column[3].hide()
                            column[3].setY(1)
                            statsController.addScoreByLevel(column[2].level.value!!)
                        }
                    } else {
                        column[3].moveUp(2)
                        column[2].moveUp(2)
                        column[1].setY(3)
                        column[0].setY(2)
                    }
                }
                "1100" -> {
                    if (column.tilesLevelEqual(0, 1)) {
                        successful = true
                        column[1].moveUp()
                        afterAnimation {
                            column[0].level.value = column[0].level.value!! + 1
                            column[1].hide()
                            column[1].setY(1)
                            statsController.addScoreByLevel(column[0].level.value!!)
                        }
                    } else continue
                }
                "0101" -> {
                    successful = true
                    if (column.tilesLevelEqual(1, 3)) {
                        column[1].moveUp()
                        column[3].moveUp(3)
                        column[0].setY(3)
                        afterAnimation {
                            column[1].level.value = column[1].level.value!! + 1
                            column[3].hide()
                            column[3].setY(1)
                            statsController.addScoreByLevel(column[1].level.value!!)
                        }
                    } else {
                        column[3].moveUp(2)
                        column[1].moveUp()
                        column[0].setY(3)
                    }
                }
                "1010" -> {
                    successful = true
                    if (column.tilesLevelEqual(0, 2)) {
                        column[2].moveUp(2)
                        afterAnimation {
                            column[0].level.value = column[0].level.value!! + 1
                            column[2].hide()
                            column[2].setY(2)
                            statsController.addScoreByLevel(column[0].level.value!!)
                        }
                    } else {
                        column[2].moveUp()
                        column[1].moveDown()
                    }
                }
                "1001" -> {
                    successful = true
                    if (column.tilesLevelEqual(0, 3)) {
                        column[3].setY(0)
                        afterAnimation {
                            column[0].level.value = column[0].level.value!! + 1
                            column[3].hide()
                            column[3].setY(3)
                            statsController.addScoreByLevel(column[0].level.value!!)
                        }
                    } else {
                        column[3].moveUp(2)
                        column[1].moveDown(2)
                    }
                }
                "0110" -> {
                    successful = true
                    if (column.tilesLevelEqual(1, 2)) {
                        column[1].moveUp()
                        column[2].moveUp(2)
                        column[0].setY(2)
                        afterAnimation {
                            column[1].level.value = column[1].level.value!! + 1
                            column[2].hide()
                            column[2].setY(1)
                            statsController.addScoreByLevel(column[1].level.value!!)
                        }
                    } else {
                        column[2].moveUp()
                        column[1].moveUp()
                        column[0].setY(2)
                    }
                }

                "0001" -> {
                    successful = true
                    column[3].setY(0)
                    column[0].setY(3)
                }
                "0010" -> {
                    successful = true
                    column[2].setY(0)
                    column[0].setY(2)
                }
                "0100" -> {
                    successful = true
                    column[1].setY(0)
                    column[0].setY(1)
                }
            }
        }
        if (successful) {
            afterAnimation {
                generator.generate()
                statsController.moves.value++
            }
        }
    }

    private fun afterAnimation(code: suspend () -> Unit) {
        coroutineScope.launch {
            delay(Constants.ANIMATION_DURATION.toLong())
            code()
        }
    }

    private fun TileData.moveLeft(squares: Int = 1) {
        this.positionX.value -= squares
    }

    private fun TileData.moveRight(squares: Int = 1) {
        this.positionX.value += squares
    }

    private fun TileData.moveUp(squares: Int = 1) {
        this.positionY.value -= squares
    }

    private fun TileData.moveDown(squares: Int = 1) {
        this.positionY.value += squares
    }

    private fun TileData.setX(x: Int) {
        this.positionX.value = x
    }

    private fun TileData.setY(y: Int) {
        this.positionY.value = y
    }

    private fun TileData.increaseLevel() {
        this.level.value = this.level.value!! + 1
    }

    private fun TileData.hide() {
        this.level.value = null
    }

    private fun Array<TileData>.tilesLevelEqual(first: Int, second: Int) = this[first].level.value == this[second].level.value
}