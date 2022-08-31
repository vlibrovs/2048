package com.vlibrovs.twentyfortyeight.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat
import java.util.*

@Entity(tableName = "games")
data class Game(
    @PrimaryKey(autoGenerate = true)
    var number: Int? = null,
    val score: Int,
    val moves: Int,
    val date: String
) {
    constructor(number: Int? = null, score: Int, moves: Int, date: Date) : this(
        number = number,
        score = score,
        moves = moves,
        date = DateFormat.getDateInstance(DateFormat.SHORT).format(date)
    )
}
