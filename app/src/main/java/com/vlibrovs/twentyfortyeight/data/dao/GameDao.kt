package com.vlibrovs.twentyfortyeight.data.dao

import androidx.room.*
import com.vlibrovs.twentyfortyeight.data.model.game.Game
import com.vlibrovs.twentyfortyeight.data.model.theme.Theme
import com.vlibrovs.twentyfortyeight.data.model.theme.ThemeEntity

@Dao
interface GameDao {

    @Query("SELECT * FROM themes")
    suspend fun getAllThemes(): List<ThemeEntity>

    @Query("SELECT * FROM games ORDER BY number DESC")
    suspend fun getAllGames(): List<Game>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertGame(game: Game)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertTheme(theme: ThemeEntity)

    @Query("DELETE FROM themes")
    suspend fun clearThemes()

    @Query("DELETE FROM games")
    suspend fun clearGames()

}