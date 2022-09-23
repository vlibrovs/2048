package com.vlibrovs.twentyfortyeight.ui.viewmodel

import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlibrovs.twentyfortyeight.common.Constants
import com.vlibrovs.twentyfortyeight.data.model.game.Game
import com.vlibrovs.twentyfortyeight.data.model.theme.DefaultThemes
import com.vlibrovs.twentyfortyeight.data.model.theme.Theme
import com.vlibrovs.twentyfortyeight.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _gameList = mutableListOf<Game>()

    val gameList: List<Game>
        get() = _gameList

    private val _themeList = mutableListOf<Theme>()

    val themeList: List<Theme>
        get() = _themeList

    val bestScore: Int
        get() {
            var best = 0
            for (game in gameList) {
                if (game.score > best) best = game.score
            }
            return best
        }

    val averageMoves: Int
        get() {
            var sum = 0
            for (game in gameList) {
                sum += game.moves
            }
            return sum / gameList.size
        }
    val averageScore: Int
        get() {
            var sum = 0
            for (game in gameList) {
                sum += game.score
            }
            return sum / gameList.size
        }

    val mostMoves: Int
        get() {
            var most = 0
            for (game in gameList) {
                if (game.moves > most) most = game.moves
            }
            return most
        }

    var selectedThemeId = mutableStateOf(-1)

    var sharedPreferences: SharedPreferences? = null

    fun getThemeById(id: Int): Theme? {
        for (theme in themeList) {
            if (theme.id == id) return theme
        }
        return null
    }

    private fun getGames() {
        _gameList.clear()
        viewModelScope.launch(Dispatchers.IO) {
            _gameList += repository.getAllGames()
        }
    }

    fun getThemes() {
        _themeList.clear()
        viewModelScope.launch(Dispatchers.IO) {
            _themeList += repository.getAllThemes()
        }
    }

    fun addTheme(theme: Theme) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveTheme(theme)
        }
    }

    fun addGame(game: Game) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveGame(game)
        }
    }

    init {
        getThemes()
        getGames()
    }
}