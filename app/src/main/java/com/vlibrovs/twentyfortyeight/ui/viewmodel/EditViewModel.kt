package com.vlibrovs.twentyfortyeight.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlibrovs.twentyfortyeight.data.model.theme.Theme
import com.vlibrovs.twentyfortyeight.data.model.theme.ThemePropertyType
import com.vlibrovs.twentyfortyeight.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class EditViewModel(private val repository: Repository, private val onThemeSave: (savedTheme: Theme) -> Unit = {}) : ViewModel() {
    var themeBuilder: Theme.Builder? = null
    var themePropertyType: ThemePropertyType? = null

    fun finish() {
        val theme = themeBuilder!!.build()
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveTheme(theme)
            onThemeSave(theme)
        }
    }
}