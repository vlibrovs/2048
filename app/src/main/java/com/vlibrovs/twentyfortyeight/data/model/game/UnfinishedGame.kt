package com.vlibrovs.twentyfortyeight.data.model.game

import com.vlibrovs.twentyfortyeight.domain.game.model.game_state.GameState
import com.vlibrovs.twentyfortyeight.domain.game.model.game_state.SizeFourGameState

class UnfinishedGame(
    number: Int? = null,
    score: Int = 0,
    moves: Int = 0,
    state: String,
    win: Boolean = false
) : Game(number, score, moves, state, false) {
    constructor(number: Int? = null, score: Int = 0, moves: Int = 0, state: GameState = SizeFourGameState(), win: Boolean = false) : this(
        number,
        score,
        moves,
        state.toString()+"w${if (win) 1 else 0}"
    )

    fun getWinState(): Boolean {
        return extra.last() == '1'
    }

    fun setWinState(winState: Boolean) {
        val sb = StringBuilder(extra)
        sb.deleteCharAt(sb.length-1).append(if (winState) 1 else 0)
        extra = sb.toString()
    }

    fun getWinString() = "w${if (getWinState()) 1 else 0}"
}