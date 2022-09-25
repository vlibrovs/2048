package com.vlibrovs.twentyfortyeight.data.repository

import androidx.compose.ui.graphics.Color
import com.vlibrovs.twentyfortyeight.data.model.Gradient
import com.vlibrovs.twentyfortyeight.data.model.game.FinishedGame
import com.vlibrovs.twentyfortyeight.data.model.game.Game
import com.vlibrovs.twentyfortyeight.data.model.game.UnfinishedGame
import com.vlibrovs.twentyfortyeight.data.model.theme.DefaultThemes
import com.vlibrovs.twentyfortyeight.data.model.theme.Theme
import com.vlibrovs.twentyfortyeight.domain.game.model.game_state.SizeFourGameState
import java.util.*

class TestRepository : Repository {

    private var nextThemeId = 0
    private var nextGameNumber = 6

    private val themes = mutableListOf(
        DefaultThemes.Main,
        Theme(
            id = -2,
            name = "Dark Theme",
            backgroundGradient = Gradient(Color.DarkGray, Color.LightGray),
            secondaryBackgroundColor = Color(0x99ffffff),
            buttonColor = Color.Green,
            textColor = Color.White,
            linesColor = Color(0x77777777),
            tileStyles = mapOf(
                Pair(
                    1, Gradient(
                        colorStart = Color(0xFF00FFFF),
                        colorEnd = Color(0xFFB3FFFF),
                    )
                ),
                Pair(
                    2, Gradient(
                        colorStart = Color(0xFF00FFBF),
                        colorEnd = Color(0xFFB3FFEC),
                    )
                ),
                Pair(
                    3, Gradient(
                        colorStart = Color(0xFF00FF80),
                        colorEnd = Color(0xFFB3FFD9),
                    )
                ),
                Pair(
                    4, Gradient(
                        colorStart = Color(0xFF00FF00),
                        colorEnd = Color(0xFFB3FFB3),
                    )
                ),
                Pair(
                    5, Gradient(
                        colorStart = Color(0xFF80FF00),
                        colorEnd = Color(0xFFD9FFB3),
                    )
                ),
                Pair(
                    6, Gradient(
                        colorStart = Color(0xFFFFFF00),
                        colorEnd = Color(0xFFFFFFB3),
                    )
                ),
                Pair(
                    7, Gradient(
                        colorStart = Color(0xFFffbf00),
                        colorEnd = Color(0xFFffecb3),
                    )
                ),
                Pair(
                    8, Gradient(
                        colorStart = Color(0xFFff8000),
                        colorEnd = Color(0xFFffd9b3),
                    )
                ),
                Pair(
                    9, Gradient(
                        colorStart = Color(0xFFff4000),
                        colorEnd = Color(0xFFffc6b3),
                    )
                ),
                Pair(
                    10, Gradient(
                        colorStart = Color(0xFFff0040),
                        colorEnd = Color(0xFFffb3c6),
                    )
                ),
                Pair(
                    11, Gradient(
                        colorStart = Color(0xFFff0080),
                        colorEnd = Color(0xFFffb3d9),
                    )
                ),
                Pair(
                    12, Gradient(
                        colorStart = Color(0xFFff00bf),
                        colorEnd = Color(0xFFffb3ec),
                    )
                ),
                Pair(
                    13, Gradient(
                        colorStart = Color(0xFFbf00ff),
                        colorEnd = Color(0xFFd9b3ff),
                    )
                ),
                Pair(
                    14, Gradient(
                        colorStart = Color(0xFF0080ff),
                        colorEnd = Color(0xFFb3d9ff),
                    )
                ),
                Pair(
                    15, Gradient(
                        colorStart = Color(0xFF0040ff),
                        colorEnd = Color(0xFFb3c6ff),
                    )
                ),
                Pair(
                    16, Gradient(
                        colorStart = Color(0xFF0000ff),
                        colorEnd = Color(0xFFb3b3ff),
                    )
                ),
                Pair(
                    17, Gradient(
                        colorStart = Color(0xFF8000ff),
                        colorEnd = Color(0xFFc6b3ff),
                    )
                )
            )
        )
    )
    private val games = mutableListOf(
        FinishedGame(1, 19287, 236, Date()),
        FinishedGame(2, 187236, 123, Date()),
        FinishedGame(3, 127863, 136, Date()),
        FinishedGame(4, 123412, 743, Date()),
        UnfinishedGame(5, 123412, 743, SizeFourGameState(first = 11)),
    )

    override suspend fun getAllThemes() = themes as List<Theme>
    override suspend fun getAllGames() = games as List<Game>

    override suspend fun saveTheme(theme: Theme) {
        if (themes.containsId(theme.id)) {
            themes.replaceById(theme.id!!, theme)
        } else {
            themes.add(theme.apply {
                id = nextThemeId
                nextThemeId++
            })
        }
    }

    override suspend fun saveGame(game: Game) {
        var update = false
        for (savedGame in games) {
            if (savedGame.number == game.number) {
                update = true
                savedGame.apply {
                    score = game.score
                    moves = game.moves
                    extra = game.extra
                    finished = game.finished
                }
            }
        }
        if (!update) {
            games.add(game.apply {
                if (number == null) {
                    number = nextGameNumber
                    nextGameNumber++
                }
            })
        }
    }

    private fun List<Theme>.containsId(id: Int?): Boolean {
        if (id == null) return false
        for (theme in this) {
            if (theme.id == id) return true
        }
        return false
    }

    private fun MutableList<Theme>.replaceById(id: Int, newTheme: Theme) {
        for (i in 0 until this.size) {
            if (this[i].id == id) this[i] = newTheme
        }
    }

}