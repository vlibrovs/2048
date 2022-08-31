package com.vlibrovs.twentyfortyeight.data.entity

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vlibrovs.twentyfortyeight.data.model.Theme
import com.vlibrovs.twentyfortyeight.data.model.TileStyle

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
        val tileStylesList = mutableListOf<TileStyle>()
        for (line in tileStyles.lines()) {
            val values = line.split('/')
            tileStylesList.add(
                TileStyle(
                    values[0].toInt(),
                    Color(values[1].toInt()),
                    Color(values[0].toInt())
                )
            )
        }
        return Theme(
            name = name,
            backgroundGradient = mutableListOf(
                Color(firstBackgroundColorInt),
                Color(secondBackgroundColorInt)
            ),
            secondaryBackgroundColor = Color(secondaryBackgroundColorInt),
            buttonColor = Color(buttonColorInt),
            textColor = Color(textColorInt),
            linesColor = Color(linesColorInt),
            tileStyles = tileStylesList
        )
    }
}
