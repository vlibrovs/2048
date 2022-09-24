package com.vlibrovs.twentyfortyeight.data.model.game

import com.vlibrovs.twentyfortyeight.domain.game.model.game_state.GameState
import com.vlibrovs.twentyfortyeight.domain.game.model.game_state.SizeFourGameState

class UnfinishedGame(
    number: Int? = null,
    score: Int = 0,
    moves: Int = 0,
    state: String
) : Game(number, score, moves, state, false) {
    constructor(number: Int? = null, score: Int = 0, moves: Int = 0, state: GameState = SizeFourGameState()) : this(
        number,
        score,
        moves,
        state.toString()
    )
}