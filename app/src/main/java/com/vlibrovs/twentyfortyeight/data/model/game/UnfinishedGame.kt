package com.vlibrovs.twentyfortyeight.data.model.game

import com.vlibrovs.twentyfortyeight.domain.game.model.game_state.GameState

class UnfinishedGame(
    number: Int? = null,
    score: Int,
    moves: Int,
    state: String
) : Game(number, score, moves, state, false) {
    constructor(number: Int?, score: Int, moves: Int, state: GameState) : this(
        number,
        score,
        moves,
        state.toString()
    )
}