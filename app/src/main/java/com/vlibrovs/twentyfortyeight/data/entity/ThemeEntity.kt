package com.vlibrovs.twentyfortyeight.data.entity

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vlibrovs.twentyfortyeight.data.model.Gradient
import com.vlibrovs.twentyfortyeight.data.model.Theme

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
        var level = 1
        for (line in tileStyles.lines()) {
            val values = line.split('/')
            tileStylesMap[level] = Gradient(
                Color(values[0].toInt()),
                Color(values[1].toInt())
            )
            level++
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
