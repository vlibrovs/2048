package com.vlibrovs.twentyfortyeight.data.model.game

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat
import java.util.*

@Entity(tableName = "games")
abstract class Game(
    @PrimaryKey(autoGenerate = true)
    var number: Int? = null,
    val score: Int,
    val moves: Int,
    val extra: String,
    var finished: Boolean = true
) {
    constructor(number: Int? = null, score: Int, moves: Int, date: Date) : this(
        number = number,
        score = score,
        moves = moves,
        extra = DateFormat.getDateInstance(DateFormat.SHORT).format(date)
    )
}
