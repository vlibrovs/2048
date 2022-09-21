package com.vlibrovs.twentyfortyeight.data.repository

import com.vlibrovs.twentyfortyeight.data.model.game.Game
import com.vlibrovs.twentyfortyeight.data.model.theme.Theme

interface Repository {
    suspend fun getAllThemes(): List<Theme>
    suspend fun getAllGames(): List<Game>
    suspend fun addTheme(theme: Theme)
    suspend fun addGame(game: Game)
}