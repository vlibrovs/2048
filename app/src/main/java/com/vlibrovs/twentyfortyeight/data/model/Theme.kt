package com.vlibrovs.twentyfortyeight.data.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import com.vlibrovs.twentyfortyeight.data.entity.ThemeEntity

data class Theme(
    var name: String,
    val backgroundGradient: MutableList<Color>,
    var secondaryBackgroundColor: Color,
    var buttonColor: Color,
    var textColor: Color,
    var linesColor: Color,
    val tileStyles: List<TileStyle> = List(16) { index -> TileStyle(index+1) },
    var isSelected: Boolean = false
) {
    fun toThemeEntity(): ThemeEntity {
        val tileStylesBuilder = StringBuilder()
        for (style in tileStyles) {
            tileStylesBuilder.appendLine(style.toString())
        }
        return ThemeEntity(
            name = name,
            firstBackgroundColorInt = backgroundGradient[0].toArgb(),
            secondBackgroundColorInt = backgroundGradient[1].toArgb(),
            secondaryBackgroundColorInt = secondaryBackgroundColor.toArgb(),
            buttonColorInt = buttonColor.toArgb(),
            textColorInt = textColor.toArgb(),
            linesColorInt = linesColor.toArgb(),
            tileStyles = tileStylesBuilder.trim().toString()
        )
    }

    companion object {
        val Main = Theme(
            name = "Main Theme",
            backgroundGradient = mutableListOf(
                Color(0xFF66FFFF),
                Color(0xFFCCFFFF),
            ),
            secondaryBackgroundColor = Color(0x75324E4E),
            buttonColor = Color(0xFF73CCCC),
            textColor = Color(0xFFF0FFFF),
            linesColor = Color(0x1E1E1E1E),
            isSelected = true
        )
    }
}