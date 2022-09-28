package com.vlibrovs.twentyfortyeight.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vlibrovs.twentyfortyeight.data.dao.GameDao
import com.vlibrovs.twentyfortyeight.data.model.game.Game
import com.vlibrovs.twentyfortyeight.data.model.theme.ThemeEntity

@Database(
    entities = [ThemeEntity::class, Game::class],
    version = 1
)
abstract class GameDatabase : RoomDatabase() {
    abstract fun getDao(): GameDao
}