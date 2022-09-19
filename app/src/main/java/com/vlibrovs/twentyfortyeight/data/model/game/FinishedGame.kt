package com.vlibrovs.twentyfortyeight.data.model.game

import java.text.DateFormat
import java.util.*

class FinishedGame(
    number: Int? = null,
    score: Int,
    moves: Int,
    date: String
): Game(number, score, moves, date, true) {
    constructor(number: Int? = null, score: Int, moves: Int, date: Date) : this(
        number = number,
        score = score,
        moves = moves,
        date = DateFormat.getDateInstance(DateFormat.SHORT).format(date)
    )
}
