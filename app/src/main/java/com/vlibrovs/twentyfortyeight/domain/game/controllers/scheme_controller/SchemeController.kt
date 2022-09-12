package com.vlibrovs.twentyfortyeight.domain.game.controllers.scheme_controller

import com.vlibrovs.twentyfortyeight.domain.game.model.TileData

interface SchemeController {
    fun getRowScheme(): Array<Array<TileData>>
    fun getColumnScheme(): Array<Array<TileData>>
}