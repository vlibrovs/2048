package com.vlibrovs.twentyfortyeight.data.model.theme

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vlibrovs.twentyfortyeight.data.model.Gradient

@Entity(tableName = "themes")
data class ThemeEntity(
    @PrimaryKey
    val name: String,
    val firstBackgroundColorInt: Int,
    val secondBackgroundColorInt: Int,
    val secondaryBackgroundColorInt: Int,
    val buttonColorInt: Int,
    val textColorInt: Int,
    val linesColorInt: Int,
    val tileStyles: String
) {
    fun toTheme(): Theme {
        val tileStylesMap = mutableMapOf<Int, Gradient>()
        for (line in tileStyles.lines()) {
            val values = line.split('/')
            val level = values[0].toInt()
            tileStylesMap[level] = Gradient(
                Color(values[1].toInt()),
                Color(values[2].toInt())
            )
        }
        return Theme(
            name = name,
            backgroundGradient = Gradient(
                Color(firstBackgroundColorInt),
                Color(secondBackgroundColorInt)
            ),
            secondaryBackgroundColor = Color(secondaryBackgroundColorInt),
            buttonColor = Color(buttonColorInt),
            textColor = Color(textColorInt),
            linesColor = Color(linesColorInt),
            tileStyles = tileStylesMap
        )
    }
}
