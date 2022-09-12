package com.vlibrovs.twentyfortyeight.domain.game.controllers.scheme_controller

import com.vlibrovs.twentyfortyeight.domain.game.model.TileData
import com.vlibrovs.twentyfortyeight.domain.game.model.game_state.GameState

class SizeFourSchemeController(private val gameState: GameState) : SchemeController {

    override fun getRowScheme(): Array<Array<TileData>> {
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

    override fun getColumnScheme(): Array<Array<TileData>> {
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

}