package com.vlibrovs.twentyfortyeight.ui.viewmodel

import android.content.SharedPreferences
import android.util.Log
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

    var selectedTheme = DefaultThemes.Main

    var sharedPreferences: SharedPreferences? = null

    fun selectTheme(newTheme: Theme) {
        for (theme in _themeList) {
            if (theme == newTheme) {
                selectedTheme = newTheme
                sharedPreferences!!.edit().apply {
                    putString(Constants.SELECTED_THEME, newTheme.name)
                    apply()
                }
            }
        }
    }

    fun selectTheme(newThemeName: String) {
        for (theme in _themeList) {
            if (theme.name == newThemeName) {
                selectedTheme = theme
                sharedPreferences!!.edit().apply {
                    putString(Constants.SELECTED_THEME, newThemeName)
                    apply()
                }
            }
        }
    }

    fun getThemeByName(name: String?): Theme? {
        for (theme in _themeList) if (theme.name == name) return theme
        return null
    }

    private fun getGames() {
        _gameList.clear()
        viewModelScope.launch(Dispatchers.IO) {
            _gameList += repository.getAllGames()
        }
    }

    private fun getThemes() {
        _themeList.clear()
        viewModelScope.launch(Dispatchers.IO) {
            _themeList += repository.getAllThemes()
        }
    }

    fun addTheme(theme: Theme) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTheme(theme)
        }
    }

    fun addGame(game: Game) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addGame(game)
        }
    }

    init {
        getThemes()
        getGames()
    }
}