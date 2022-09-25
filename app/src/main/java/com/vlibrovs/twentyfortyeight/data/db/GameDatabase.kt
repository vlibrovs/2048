package com.vlibrovs.twentyfortyeight.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vlibrovs.twentyfortyeight.data.dao.GameDao
import com.vlibrovs.twentyfortyeight.data.model.game.Game
import com.vlibrovs.twentyfortyeight.data.model.theme.Theme
import com.vlibrovs.twentyfortyeight.data.model.theme.ThemeTypeConverter

@TypeConverters(ThemeTypeConverter::class)
@Database(
    entities = [Theme::class, Game::class],
    version = 1
)
abstract class GameDatabase : RoomDatabase() {
    abstract fun getDao(): GameDao
}