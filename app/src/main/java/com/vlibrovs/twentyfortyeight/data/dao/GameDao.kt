package com.vlibrovs.twentyfortyeight.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.vlibrovs.twentyfortyeight.data.model.game.Game
import com.vlibrovs.twentyfortyeight.data.model.theme.ThemeEntity

@Dao
interface GameDao {

    @Query("SELECT * FROM themes")
    suspend fun getAllThemes(): List<ThemeEntity>

    @Query("SELECT * FROM games ORDER BY number DESC")
    suspend fun getAllGames(): List<Game>

}