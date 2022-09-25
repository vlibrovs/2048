package com.vlibrovs.twentyfortyeight.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vlibrovs.twentyfortyeight.data.model.game.Game
import com.vlibrovs.twentyfortyeight.data.model.theme.Theme

@Dao
interface GameDao {

    @Query("SELECT * FROM themes")
    suspend fun getAllThemes(): List<Theme>

    @Query("SELECT * FROM games ORDER BY number DESC")
    suspend fun getAllGames(): List<Game>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertGame(game: Game)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertTheme(theme: Theme)

}