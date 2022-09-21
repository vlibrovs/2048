package com.vlibrovs.twentyfortyeight.data.model.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.TypeConverter
import com.vlibrovs.twentyfortyeight.data.model.Gradient

interface ThemeTypeConverter {

    @TypeConverter
    fun toColor(colorInt: Int) = Color(colorInt)

    @TypeConverter
    fun fromColor(color: Color) = color.toArgb()

    @TypeConverter
    fun fromGradient(gradient: Gradient) = "${gradient.colorStart.toArgb()}/${gradient.colorEnd.toArgb()}"

    @TypeConverter
    fun toGradient(gradientString: String): Gradient {
        val colorsList = gradientString.split('/').map { string -> Color(string.toInt()) }
        return Gradient(colorStart = colorsList[0], colorEnd = colorsList[1])
    }

    @TypeConverter
    fun fromTilesStyles(tilesStyle: Map<Int, Gradient>): String {
        val sb = StringBuilder()
        for (level in 1..17) {
            sb.appendLine("$level ${fromGradient(tilesStyle[level]!!)}")
        }
        return sb.toString()
    }

    @TypeConverter
    fun toTilesStyles(tilesStyleString: String): Map<Int, Gradient> {
        val map = mutableMapOf<Int, Gradient>()
        for (line in tilesStyleString.lines()) {
            val strings = line.split(' ')
            if (strings.size == 2) {
                val level = strings[0].toInt()
                map[level] = toGradient(strings[1])
            }
        }
        if (map.size == 17) return map else throw IllegalArgumentException("Illegal string provided as an argument of ThemeTypeConverter#toTilesStyles")
    }

}