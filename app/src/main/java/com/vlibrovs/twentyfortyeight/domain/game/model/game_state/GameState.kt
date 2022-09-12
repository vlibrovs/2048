package com.vlibrovs.twentyfortyeight.domain.game.model.game_state

import com.vlibrovs.twentyfortyeight.domain.game.model.TileData

abstract class GameState(val rows: Int, val columns: Int) : Collection<TileData> {
    abstract operator fun get(index: Int): TileData
}