package com.vlibrovs.twentyfortyeight.data.model.theme

sealed class ThemePropertyType {
    data class BackgroundColor(val position: ColorPosition) : ThemePropertyType()
    object SecondaryBackgroundColor : ThemePropertyType()
    object ButtonColor : ThemePropertyType()
    object TextColor : ThemePropertyType()
    object LinesColor : ThemePropertyType()
    data class TileColor(val level: Int, val colorPosition: ColorPosition) : ThemePropertyType()
}
enum class ColorPosition {
    START, END
}