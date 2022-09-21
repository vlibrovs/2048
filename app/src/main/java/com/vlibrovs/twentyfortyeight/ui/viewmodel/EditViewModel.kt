package com.vlibrovs.twentyfortyeight.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.vlibrovs.twentyfortyeight.data.model.theme.Theme
import com.vlibrovs.twentyfortyeight.data.model.theme.ThemePropertyType

class EditViewModel : ViewModel() {
    var themeBuilder: Theme.Builder? = null
    var themePropertyType: ThemePropertyType? = null

    fun finish() {

    }
}