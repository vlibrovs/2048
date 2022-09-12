package com.vlibrovs.twentyfortyeight.domain.game.controllers.generator

import androidx.compose.runtime.MutableState
import com.vlibrovs.twentyfortyeight.domain.game.model.TileData
import com.vlibrovs.twentyfortyeight.domain.game.model.game_state.GameState
import kotlin.random.Random

class Generator(private val gameState: GameState, private val gameEndState: MutableState<Boolean>) {
    fun generate() {
        val emptySquaresList = mutableListOf<TileData>()
        for (tileData in gameState) {
            if (tileData.level.value == null) emptySquaresList.add(tileData)
        }
        if (emptySquaresList.size == 0) {
            gameEndState.value = true
            return
        }
        val position = Random.nextInt(emptySquaresList.size)
        val level = if (Random.nextInt(9) == 0) 2 else 1
        emptySquaresList[position].apply {
            justCreated.value = true
            this.level.value = level
        }
    }
}
