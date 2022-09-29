package com.vlibrovs.twentyfortyeight.domain.game.controllers.move_controller

import com.vlibrovs.twentyfortyeight.common.Constants
import com.vlibrovs.twentyfortyeight.data.model.game.UnfinishedGame
import com.vlibrovs.twentyfortyeight.data.model.game_result.GameResult
import com.vlibrovs.twentyfortyeight.domain.game.controllers.generator.Generator
import com.vlibrovs.twentyfortyeight.domain.game.controllers.scheme_controller.SchemeController
import com.vlibrovs.twentyfortyeight.domain.game.controllers.stats_controller.StatsController
import com.vlibrovs.twentyfortyeight.domain.game.model.TileData
import com.vlibrovs.twentyfortyeight.domain.game.model.game_state.GameState
import com.vlibrovs.twentyfortyeight.ui.viewmodel.MainViewModel
import kotlinx.coroutines.*

class SizeFourMoveController(
    private val gameState: GameState,
    private val schemeController: SchemeController,
    private val generator: Generator,
    private val statsController: StatsController,
    private val coroutineScope: CoroutineScope,
    private val game: UnfinishedGame,
    private val viewModel: MainViewModel
) : MoveController(gameState, schemeController, generator, statsController, coroutineScope) {

    override fun moveRight() {
        for (tileData in gameState) tileData.justCreated.value = false
        var successful = false
        val scheme = schemeController.getRowScheme()
        var win = false
        for (row in scheme) {
            when (String(CharArray(4) { index -> if (row[index].level.value == null) '0' else '1' })) {
                "1111" -> {
                    if (row.tilesLevelEqual(2, 3) && row.tilesLevelEqual(0, 1)) {
                        row[2].moveRight()
                        row[1].moveRight()
                        row[0].moveRight(2)
                        afterAnimation {
                            row[0].hide()
                            row[1].increaseLevel()
                            if (!win && row[1].is2048()) win = true
                            row[2].hide()
                            row[3].increaseLevel()
                            if (!win && row[3].is2048()) win = true
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
                            if (!win && row[3].is2048()) win = true

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
                            if (!win && row[2].is2048()) win = true
                            row[1].hide()
                            row[1].setX(0)
                            statsController.addScoreByLevel(row[2].level.value!!)
                        }
                        successful = true
                    } else if (row.tilesLevelEqual(0, 1)) {
                        row[0].moveRight()
                        afterAnimation {
                            row[1].increaseLevel()
                            if (!win && row[1].is2048()) win = true
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
                            if (!win && row[2].is2048()) win = true
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
                            if (!win && row[1].is2048()) win = true
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
                            if (!win && row[3].is2048()) win = true
                            row[2].hide()
                            row[2].setX(1)
                            statsController.addScoreByLevel(row[3].level.value!!)
                        }
                        successful = true
                    } else if (row.tilesLevelEqual(1, 2)) {
                        row[1].moveRight()
                        afterAnimation {
                            row[2].increaseLevel()
                            if (!win && row[2].is2048()) win = true
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
                            if (!win && row[3].is2048()) win = true
                            row[2].hide()
                            row[2].setX(0)
                            statsController.addScoreByLevel(row[3].level.value!!)
                        }
                    } else if (row.tilesLevelEqual(0, 2)) {
                        row[0].moveRight(2)
                        afterAnimation {
                            row[2].increaseLevel()
                            if (!win && row[2].is2048()) win = true
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
                            if (!win && row[3].is2048()) win = true
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
                            if (!win && row[1].is2048()) win = true
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
                            if (!win && row[1].is2048()) win = true
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
                            if (!win && row[2].is2048()) win = true
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
                            if (!win && row[3].is2048()) win = true
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
                            if (!win && row[3].is2048()) win = true
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
                            if (!win && row[2].is2048()) win = true
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
                            if (!win && row[3].is2048()) win = true
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
            onMoveSuccess()
            afterAnimation {
                checkWin(!game.getWinState() && win)
                checkLoss()
            }
        }
    }

    override fun moveLeft() {
        for (tileData in gameState) tileData.justCreated.value = false
        var successful = false
        val scheme = schemeController.getRowScheme()
        var win = false
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
                            if (!win && row[2].is2048()) win = true
                            row[1].hide()
                            row[0].increaseLevel()
                            if (!win && row[0].is2048()) win = true
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
                            if (!win && row[0].is2048()) win = true
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
                            if (!win && row[1].is2048()) win = true
                            row[2].hide()
                            row[2].setX(3)
                            statsController.addScoreByLevel(row[1].level.value!!)
                        }
                    } else if (row.tilesLevelEqual(2, 3)) {
                        successful = true
                        row[3].moveLeft()
                        afterAnimation {
                            row[2].increaseLevel()
                            if (!win && row[2].is2048()) win = true
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
                            if (!win && row[0].is2048()) win = true
                            row[1].hide()
                            row[1].setX(2)
                            statsController.addScoreByLevel(row[0].level.value!!)
                        }
                    } else if (row.tilesLevelEqual(1, 2)) {
                        successful = true
                        row[2].moveLeft()
                        afterAnimation {
                            row[1].increaseLevel()
                            if (!win && row[1].is2048()) win = true
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
                            if (!win && row[1].is2048()) win = true
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
                            if (!win && row[2].is2048()) win = true
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
                            if (!win && row[0].is2048()) win = true
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
                            if (!win && row[2].is2048()) win = true
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
                            if (!win && row[0].is2048()) win = true
                            row[1].hide()
                            row[1].setX(3)
                            statsController.addScoreByLevel(row[0].level.value!!)
                        }
                    } else if (row.tilesLevelEqual(1, 3)) {
                        row[3].moveLeft(2)
                        afterAnimation {
                            row[1].increaseLevel()
                            if (!win && row[1].is2048()) win = true
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
                            if (!win && row[2].is2048()) win = true
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
                            if (!win && row[0].is2048()) win = true
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
                            if (!win && row[1].is2048()) win = true
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
                            if (!win && row[0].is2048()) win = true
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
                            if (!win && row[0].is2048()) win = true
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
                            if (!win && row[1].is2048()) win = true
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
            onMoveSuccess()
            afterAnimation {
                checkWin(!game.getWinState() && win)
                checkLoss()
            }
        }
    }

    override fun moveDown() {
        for (tileData in gameState) tileData.justCreated.value = false
        var successful = false
        val scheme = schemeController.getColumnScheme()
        var win = false
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
                            column[1].increaseLevel()
                            if (!win && column[1].is2048()) win = true
                            column[2].hide()
                            column[3].increaseLevel()
                            if (!win && column[3].is2048()) win = true
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
                            column[3].increaseLevel()
                            if (!win && column[3].is2048()) win = true
                            column[2].hide()
                            column[2].setY(0)
                            statsController.addScoreByLevel(column[3].level.value!!)
                        }
                    } else if (column.tilesLevelEqual(1, 2)) {
                        successful = true
                        column[0].moveDown()
                        column[1].moveDown()
                        afterAnimation {
                            column[2].increaseLevel()
                            if (!win && column[2].is2048()) win = true
                            column[1].hide()
                            column[1].setY(0)
                            statsController.addScoreByLevel(column[2].level.value!!)
                        }
                    } else if (column.tilesLevelEqual(0, 1)) {
                        successful = true
                        column[0].moveDown()
                        afterAnimation {
                            column[1].increaseLevel()
                            if (!win && column[1].is2048()) win = true
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
                            column[2].increaseLevel()
                            if (!win && column[2].is2048()) win = true
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
                            column[1].increaseLevel()
                            if (!win && column[1].is2048()) win = true
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
                            column[3].increaseLevel()
                            if (!win && column[3].is2048()) win = true
                            column[2].hide()
                            column[2].setY(1)
                            statsController.addScoreByLevel(column[3].level.value!!)
                        }
                    } else if (column.tilesLevelEqual(1, 2)) {
                        successful = true
                        column[1].moveDown()
                        afterAnimation {
                            column[2].increaseLevel()
                            if (!win && column[2].is2048()) win = true
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
                            column[3].increaseLevel()
                            if (!win && column[3].is2048()) win = true
                            column[2].hide()
                            column[2].setY(0)
                            statsController.addScoreByLevel(column[3].level.value!!)
                        }
                    } else if (column.tilesLevelEqual(0, 2)) {
                        column[0].moveDown(2)
                        afterAnimation {
                            column[2].increaseLevel()
                            if (!win && column[2].is2048()) win = true
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
                            column[3].increaseLevel()
                            if (!win && column[3].is2048()) win = true
                            column[1].hide()
                            column[1].setY(0)
                            statsController.addScoreByLevel(column[3].level.value!!)
                        }
                    } else if (column.tilesLevelEqual(0, 1)) {
                        column[1].moveDown()
                        column[0].moveDown(2)
                        column[2].setY(1)
                        afterAnimation {
                            column[1].increaseLevel()
                            if (!win && column[1].is2048()) win = true
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
                            column[1].increaseLevel()
                            if (!win && column[1].is2048()) win = true
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
                            column[2].increaseLevel()
                            if (!win && column[2].is2048()) win = true
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
                            column[3].increaseLevel()
                            if (!win && column[3].is2048()) win = true
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
                            column[3].increaseLevel()
                            if (!win && column[3].is2048()) win = true
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
                            column[2].increaseLevel()
                            if (!win && column[2].is2048()) win = true
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
                            column[3].increaseLevel()
                            if (!win && column[3].is2048()) win = true
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
            onMoveSuccess()
            afterAnimation {
                checkWin(!game.getWinState() && win)
                checkLoss()
            }
        }
    }

    override fun moveUp() {
        for (tileData in gameState) tileData.justCreated.value = false
        val scheme = schemeController.getColumnScheme()
        var successful = false
        var win = false
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
                            column[2].increaseLevel()
                            if (!win && column[2].is2048()) win = true
                            column[1].hide()
                            column[0].increaseLevel()
                            if (!win && column[0].is2048()) win = true
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
                            column[0].increaseLevel()
                            if (!win && column[0].is2048()) win = true
                            column[1].hide()
                            column[1].setY(3)
                            statsController.addScoreByLevel(column[0].level.value!!)
                        }
                    } else if (column.tilesLevelEqual(1, 2)) {
                        successful = true
                        column[3].moveUp()
                        column[2].moveUp()
                        afterAnimation {
                            column[1].increaseLevel()
                            if (!win && column[1].is2048()) win = true
                            column[2].hide()
                            column[2].setY(3)
                            statsController.addScoreByLevel(column[1].level.value!!)
                        }
                    } else if (column.tilesLevelEqual(2, 3)) {
                        successful = true
                        column[3].moveUp()
                        afterAnimation {
                            column[2].increaseLevel()
                            if (!win && column[2].is2048()) win = true
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
                            column[0].increaseLevel()
                            if (!win && column[0].is2048()) win = true
                            column[1].hide()
                            column[1].setY(2)
                            statsController.addScoreByLevel(column[0].level.value!!)
                        }
                    } else if (column.tilesLevelEqual(1, 2)) {
                        successful = true
                        column[2].moveUp()
                        afterAnimation {
                            column[1].increaseLevel()
                            if (!win && column[1].is2048()) win = true
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
                            column[1].increaseLevel()
                            if (!win && column[1].is2048()) win = true
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
                            column[2].increaseLevel()
                            if (!win && column[2].is2048()) win = true
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
                            column[0].increaseLevel()
                            if (!win && column[0].is2048()) win = true
                            column[2].hide()
                            column[2].setY(3)
                            statsController.addScoreByLevel(column[0].level.value!!)
                        }
                    } else if (column.tilesLevelEqual(2, 3)) {
                        column[2].moveUp()
                        column[3].moveUp(2)
                        column[1].setY(2)
                        afterAnimation {
                            column[2].increaseLevel()
                            if (!win && column[2].is2048()) win = true
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
                            column[0].increaseLevel()
                            if (!win && column[0].is2048()) win = true
                            column[1].hide()
                            column[1].setY(3)
                            statsController.addScoreByLevel(column[0].level.value!!)
                        }
                    } else if (column.tilesLevelEqual(1, 3)) {
                        column[3].moveUp(2)
                        afterAnimation {
                            column[1].increaseLevel()
                            if (!win && column[1].is2048()) win = true
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
                            column[2].increaseLevel()
                            if (!win && column[2].is2048()) win = true
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
                            column[0].increaseLevel()
                            if (!win && column[0].is2048()) win = true
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
                            column[1].increaseLevel()
                            if (!win && column[1].is2048()) win = true
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
                            column[0].increaseLevel()
                            if (!win && column[0].is2048()) win = true
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
                            column[0].increaseLevel()
                            if (!win && column[0].is2048()) win = true
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
                            column[1].increaseLevel()
                            if (!win && column[1].is2048()) win = true
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
            onMoveSuccess()
            afterAnimation {
                checkWin(!game.getWinState() && win)
                checkLoss()
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

    private fun Array<TileData>.tilesLevelEqual(first: Int, second: Int) =
        this[first].level.value == this[second].level.value

    private fun TileData.is2048(): Boolean {
        return this.level.value == 11
    }

    private fun onMoveSuccess() {
        afterAnimation {
            generator.generate()
            statsController.makeMove()
            val winString = game.getWinString()
            game.extra = gameState.toString() + winString
        }
    }

    private fun checkWin(win: Boolean) {
        if (win) {
            game.setWinState(true)
            viewModel.gameResult.value = GameResult.WIN
        }
    }

    private fun checkLoss() {
        for (row in schemeController.getRowScheme()) {
            for (tileData in row) {
                if (tileData.level.value == null) {
                    return
                }
            }
            if (row.tilesLevelEqual(0, 1) || row.tilesLevelEqual(1, 2) || row.tilesLevelEqual(2, 3)) {
                return
            }
        }
        for (column in schemeController.getColumnScheme()) {
            for (tileData in column) {
                if (tileData.level.value == null) {
                    return
                }
            }
            if (column.tilesLevelEqual(0, 1) || column.tilesLevelEqual(1, 2) || column.tilesLevelEqual(2, 3)) {
                return
            }
        }
        viewModel.gameResult.value = GameResult.LOSS
    }
}