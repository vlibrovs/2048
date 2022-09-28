package com.vlibrovs.twentyfortyeight.data.model.game

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vlibrovs.twentyfortyeight.domain.game.model.game_state.SizeFourGameState
import java.text.DateFormat
import java.util.*

@Entity(tableName = "games")
open class Game(
    @PrimaryKey(autoGenerate = true)
    var number: Int? = null,
    var score: Int,
    var moves: Int,
    var extra: String,
    var finished: Boolean = true
) {
    constructor(number: Int? = null, score: Int, moves: Int, date: Date) : this(
        number = number,
        score = score,
        moves = moves,
        extra = DateFormat.getDateInstance(DateFormat.SHORT).format(date)
    )

    fun toSizeFourUnfinishedGame() = UnfinishedGame(
        number,
        score,
        moves,
        SizeFourGameState.fromString(extra)!!
    )
}
