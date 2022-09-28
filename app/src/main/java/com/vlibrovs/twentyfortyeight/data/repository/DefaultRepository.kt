package com.vlibrovs.twentyfortyeight.data.repository

import com.vlibrovs.twentyfortyeight.data.db.GameDatabase
import com.vlibrovs.twentyfortyeight.data.model.game.Game
import com.vlibrovs.twentyfortyeight.data.model.theme.Theme

class DefaultRepository(database: GameDatabase) : Repository {

    private val dao = database.getDao()

    override suspend fun getAllThemes(): List<Theme> {
        return dao.getAllThemes().map { themeEntity -> themeEntity.toTheme() }
    }

    override suspend fun getAllGames(): List<Game> {
        return dao.getAllGames()
    }

    override suspend fun saveTheme(theme: Theme) {
        dao.upsertTheme(theme.toEntity())
    }

    override suspend fun clearThemes() {
        dao.clearThemes()
    }

    override suspend fun clearGames() {
        dao.clearGames()
    }

    override suspend fun saveGame(game: Game) {
        dao.upsertGame(game)
    }
}