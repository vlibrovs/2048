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
            isSelected = true,
            tileStyles = listOf(
                TileStyle(
                    tileLevel = 1,
                    colorStart = Color(0xFF00FFFF),
                    colorEnd = Color(0xFFB3FFFF),
                ),
                TileStyle(
                    tileLevel = 2,
                    colorStart = Color(0xFF00FFBF),
                    colorEnd = Color(0xFFB3FFEC),
                ),
                TileStyle(
                    tileLevel = 3,
                    colorStart = Color(0xFF00FF80),
                    colorEnd = Color(0xFFB3FFD9),
                ),
                TileStyle(
                    tileLevel = 4,
                    colorStart = Color(0xFF00FF00),
                    colorEnd = Color(0xFFB3FFB3),
                ),
                TileStyle(
                    tileLevel = 5,
                    colorStart = Color(0xFF80FF00),
                    colorEnd = Color(0xFFD9FFB3),
                ),
                TileStyle(
                    tileLevel = 6,
                    colorStart = Color(0xFFFFFF00),
                    colorEnd = Color(0xFFFFFFB3),
                ),
                TileStyle(
                    tileLevel = 7,
                    colorStart = Color(0xFFffbf00),
                    colorEnd = Color(0xFFffecb3),
                ),
                TileStyle(
                    tileLevel = 8,
                    colorStart = Color(0xFFff8000),
                    colorEnd = Color(0xFFffd9b3),
                ),
                TileStyle(
                    tileLevel = 9,
                    colorStart = Color(0xFFff4000),
                    colorEnd = Color(0xFFffc6b3),
                ),
                TileStyle(
                    tileLevel = 10,
                    colorStart = Color(0xFFff0040),
                    colorEnd = Color(0xFFffb3c6),
                ),
                TileStyle(
                    tileLevel = 11,
                    colorStart = Color(0xFFff0080),
                    colorEnd = Color(0xFFffb3d9),
                ),
                TileStyle(
                    tileLevel = 12,
                    colorStart = Color(0xFFff00bf),
                    colorEnd = Color(0xFFffb3ec),
                ),
                TileStyle(
                    tileLevel = 13,
                    colorStart = Color(0xFFbf00ff),
                    colorEnd = Color(0xFFd9b3ff),
                ),
                TileStyle(
                    tileLevel = 14,
                    colorStart = Color(0xFF0080ff),
                    colorEnd = Color(0xFFb3d9ff),
                ),
                TileStyle(
                    tileLevel = 15,
                    colorStart = Color(0xFF0040ff),
                    colorEnd = Color(0xFFb3c6ff),
                ),
                TileStyle(
                    tileLevel = 16,
                    colorStart = Color(0xFF0000ff),
                    colorEnd = Color(0xFFb3b3ff),
                ),
                TileStyle(
                    tileLevel = 17,
                    colorStart = Color(0xFF8000ff),
                    colorEnd = Color(0xFFc6b3ff),
                )
            )
        )
    }
}