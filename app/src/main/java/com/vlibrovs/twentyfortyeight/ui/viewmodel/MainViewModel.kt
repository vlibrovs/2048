package com.vlibrovs.twentyfortyeight.ui.viewmodel

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlibrovs.twentyfortyeight.common.Constants
import com.vlibrovs.twentyfortyeight.data.model.game.FinishedGame
import com.vlibrovs.twentyfortyeight.data.model.game.Game
import com.vlibrovs.twentyfortyeight.data.model.game.UnfinishedGame
import com.vlibrovs.twentyfortyeight.data.model.game_result.GameResult
import com.vlibrovs.twentyfortyeight.data.model.theme.DefaultThemes
import com.vlibrovs.twentyfortyeight.data.model.theme.Theme
import com.vlibrovs.twentyfortyeight.data.repository.Repository
import com.vlibrovs.twentyfortyeight.domain.game.model.game_state.SizeFourGameState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import java.util.*

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _gameList = mutableListOf<Game>()

    val gameList: List<Game>
        get() = _gameList

    private val _themeList = mutableListOf(DefaultThemes.Main)

    val themeList: List<Theme>
        get() = _themeList

    val bestScore: Int
        get() {
            if (gameList.isEmpty()) return 0
            var best = 0
            for (game in gameList) {
                if (game.score > best) best = game.score
            }
            return best
        }

    val averageMoves: Int
        get() {
            if (gameList.isEmpty()) return 0
            var sum = 0
            for (game in gameList) {
                sum += game.moves
            }
            return sum / gameList.size
        }
    val averageScore: Int
        get() {
            if (gameList.isEmpty()) return 0
            var sum = 0
            for (game in gameList) {
                sum += game.score
            }
            return sum / gameList.size
        }

    val mostMoves: Int
        get() {
            if (gameList.isEmpty()) return 0
            var most = 0
            for (game in gameList) {
                if (game.moves > most) most = game.moves
            }
            return most
        }

    var gameSaver: () -> Unit = {}

    var selectedThemeId = mutableStateOf(-1)

    var sharedPreferences: SharedPreferences? = null

    fun getThemeById(id: Int): Theme? {
        for (theme in themeList) {
            if (theme.id == id) return theme
        }
        return null
    }

    fun selectThemeById(id: Int) {
        selectedThemeId.value = id
        sharedPreferences!!.edit().putString(Constants.SELECTED_THEME_ID, id.toString()).apply()
    }

    fun finishCurrentGame() {
        viewModelScope.launch(Dispatchers.IO) {
            for (game in _gameList) if (!game.finished) {
                saveGame(FinishedGame(
                    number = game.number,
                    score = game.score,
                    moves = game.moves,
                    date = Date()
                ))
            }
        }
    }

    private fun getGames() {
        _gameList.clear()
        viewModelScope.launch(Dispatchers.IO) {
            _gameList += repository.getAllGames().reversed()
        }
    }

    fun getThemes() {
        _themeList.clear()
        _themeList.add(DefaultThemes.Main)
        viewModelScope.launch(Dispatchers.IO) {
            _themeList += repository.getAllThemes()
        }
    }

    val gameResult = mutableStateOf(GameResult.EMPTY)

    fun saveGame(game: Game) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveGame(game)
            getGames()
            Log.d("Games", "Games: ${gameList.size}")
        }
    }

    fun getCurrentGame(): Game? {
        for (game in gameList) {
            if (!game.finished) return game
        }
        return null
    }

    private fun clearThemes() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearThemes()
        }
    }

    private fun clearGames() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearGames()
        }
    }

    init {
        clearGames()
        saveGame(
            UnfinishedGame(
                number = null,
                score = 1000,
                moves = 100,
                state = SizeFourGameState(
                    thirteenth = 10,
                    fourteenth = 10,
                    first = 9,
                    second = 9,
                    third = 9,
                    fourth = 9
                )
            )
        )
        getThemes()
        getGames()
    }
}