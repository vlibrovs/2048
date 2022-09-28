package com.vlibrovs.twentyfortyeight.data.model.theme

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vlibrovs.twentyfortyeight.data.model.Gradient

@Entity(tableName = "themes")
data class ThemeEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String,
    var backgroundStart: Int,
    var backgroundEnd: Int,
    var secondaryBackgroundColor: Int,
    var buttonColor: Int,
    var textColor: Int,
    var linesColor: Int,
    var tileStylesCode: String
) {
    fun toTheme(): Theme {
        val map = mutableMapOf<Int, Gradient>()
        for (line in tileStylesCode.lines()) {
            val strings = line.split(' ')
            if (strings.size == 2) {
                val level = strings[0].toInt()
                map[level] = toGradient(strings[1])
            }
        }
        return Theme(
            id = id,
            name = name,
            backgroundGradient = Gradient(Color(backgroundStart), Color(backgroundEnd)),
            secondaryBackgroundColor = Color(secondaryBackgroundColor),
            buttonColor = Color(buttonColor),
            linesColor = Color(linesColor),
            textColor = Color(textColor),
            tileStyles = map
        )
    }

    private fun toGradient(gradientString: String): Gradient {
        val colorsList = gradientString.split('/').map { string -> Color(string.toInt()) }
        return Gradient(colorStart = colorsList[0], colorEnd = colorsList[1])
    }
}