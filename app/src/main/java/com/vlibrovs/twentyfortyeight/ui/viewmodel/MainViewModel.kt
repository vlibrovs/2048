package com.vlibrovs.twentyfortyeight.ui.viewmodel

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlibrovs.twentyfortyeight.common.Constants
import com.vlibrovs.twentyfortyeight.data.model.game.Game
import com.vlibrovs.twentyfortyeight.data.model.theme.Theme
import com.vlibrovs.twentyfortyeight.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val TAG = "Themes"

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

    val selectedTheme = mutableStateOf(Theme.Main)

    var sharedPreferences: SharedPreferences? = null

    fun selectTheme(newTheme: Theme) {
        for (theme in _themeList) {
            if (theme == newTheme) {
                selectedTheme.value = newTheme
                sharedPreferences!!.edit().apply{
                    putString(Constants.SELECTED_THEME, newTheme.name)
                    apply()
                }
            }
        }
        Log.d(TAG, "selectTheme: New theme is ${sharedPreferences!!.getString(Constants.SELECTED_THEME, "")}")
    }

    fun selectTheme(newThemeName: String) {
        for (theme in _themeList) {
            if (theme.name == newThemeName) {
                selectedTheme.value = theme
                sharedPreferences!!.edit().apply{
                    putString(Constants.SELECTED_THEME, newThemeName)
                    apply()
                }
            }
        }
        Log.d(TAG, "selectTheme: New theme is ${sharedPreferences!!.getString(Constants.SELECTED_THEME, "")}")
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

    init {
        getThemes()
        getGames()
    }
}