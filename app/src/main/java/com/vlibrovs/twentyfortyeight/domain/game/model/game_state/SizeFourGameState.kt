package com.vlibrovs.twentyfortyeight.domain.game.model.game_state

import androidx.compose.runtime.mutableStateOf
import com.vlibrovs.twentyfortyeight.domain.game.model.TileData

class SizeFourGameState(
    first: Int? = null,
    second: Int? = null,
    third: Int? = null,
    fourth: Int? = null,
    fifth: Int? = null,
    sixth: Int? = null,
    seventh: Int? = null,
    eighth: Int? = null,
    ninth: Int? = null,
    tenth: Int? = null,
    eleventh: Int? = null,
    twelvth: Int? = null,
    thirteenth: Int? = null,
    fourteenth: Int? = null,
    fifteenth: Int? = null,
    sixteenth: Int? = null,
) : GameState(rows = 4, columns = 4) {

    private val values = listOf(
        TileData(
            level = mutableStateOf(first),
            positionX = mutableStateOf(0),
            positionY = mutableStateOf(0)
        ),
        TileData(
            level = mutableStateOf(second),
            positionX = mutableStateOf(1),
            positionY = mutableStateOf(0)
        ),
        TileData(
            level = mutableStateOf(third),
            positionX = mutableStateOf(2),
            positionY = mutableStateOf(0)
        ),
        TileData(
            level = mutableStateOf(fourth),
            positionX = mutableStateOf(3),
            positionY = mutableStateOf(0)
        ),
        TileData(
            level = mutableStateOf(fifth),
            positionX = mutableStateOf(0),
            positionY = mutableStateOf(1)
        ),
        TileData(
            level = mutableStateOf(sixth),
            positionX = mutableStateOf(1),
            positionY = mutableStateOf(1)
        ),
        TileData(
            level = mutableStateOf(seventh),
            positionX = mutableStateOf(2),
            positionY = mutableStateOf(1)
        ),
        TileData(
            level = mutableStateOf(eighth),
            positionX = mutableStateOf(3),
            positionY = mutableStateOf(1)
        ),
        TileData(
            level = mutableStateOf(ninth),
            positionX = mutableStateOf(0),
            positionY = mutableStateOf(2)
        ),
        TileData(
            level = mutableStateOf(tenth),
            positionX = mutableStateOf(1),
            positionY = mutableStateOf(2)
        ),
        TileData(
            level = mutableStateOf(eleventh),
            positionX = mutableStateOf(2),
            positionY = mutableStateOf(2)
        ),
        TileData(
            level = mutableStateOf(twelvth),
            positionX = mutableStateOf(3),
            positionY = mutableStateOf(2)
        ),
        TileData(
            level = mutableStateOf(thirteenth),
            positionX = mutableStateOf(0),
            positionY = mutableStateOf(3)
        ),
        TileData(
            level = mutableStateOf(fourteenth),
            positionX = mutableStateOf(1),
            positionY = mutableStateOf(3)
        ),
        TileData(
            level = mutableStateOf(fifteenth),
            positionX = mutableStateOf(2),
            positionY = mutableStateOf(3)
        ),
        TileData(
            level = mutableStateOf(sixteenth),
            positionX = mutableStateOf(3),
            positionY = mutableStateOf(3)
        )
    )

    override val size: Int
        get() = 16

    override fun contains(element: TileData) = values.contains(element)

    override fun containsAll(elements: Collection<TileData>) = values.containsAll(elements)

    override fun isEmpty() = false

    override fun iterator(): Iterator<TileData> = values.iterator()

    override fun get(index: Int) = values[index]
}