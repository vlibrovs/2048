package com.vlibrovs.twentyfortyeight.data.model.theme

import androidx.compose.ui.graphics.Color
import com.vlibrovs.twentyfortyeight.common.Constants
import com.vlibrovs.twentyfortyeight.data.model.Gradient

object DefaultThemes {

    val Main = Theme(
        name = Constants.MAIN_THEME_NAME,
        backgroundGradient = Gradient(
            Color(0xFF66FFFF),
            Color(0xFFCCFFFF),
        ),
        secondaryBackgroundColor = Color(0x75324E4E),
        buttonColor = Color(0xFF73CCCC),
        textColor = Color(0xFFF0FFFF),
        linesColor = Color(0x1E1E1E1E),
        tileStyles = mapOf(
            Pair(
                1, Gradient(
                    colorStart = Color(0xFF00FFFF),
                    colorEnd = Color(0xFFB3FFFF),
                )
            ),
            Pair(
                2, Gradient(
                    colorStart = Color(0xFF00FFBF),
                    colorEnd = Color(0xFFB3FFEC),
                )
            ),
            Pair(
                3, Gradient(
                    colorStart = Color(0xFF00FF80),
                    colorEnd = Color(0xFFB3FFD9),
                )
            ),
            Pair(
                4, Gradient(
                    colorStart = Color(0xFF00FF00),
                    colorEnd = Color(0xFFB3FFB3),
                )
            ),
            Pair(
                5, Gradient(
                    colorStart = Color(0xFF80FF00),
                    colorEnd = Color(0xFFD9FFB3),
                )
            ),
            Pair(
                6, Gradient(
                    colorStart = Color(0xFFFFFF00),
                    colorEnd = Color(0xFFFFFFB3),
                )
            ),
            Pair(
                7, Gradient(
                    colorStart = Color(0xFFffbf00),
                    colorEnd = Color(0xFFffecb3),
                )
            ),
            Pair(
                8, Gradient(
                    colorStart = Color(0xFFff8000),
                    colorEnd = Color(0xFFffd9b3),
                )
            ),
            Pair(
                9, Gradient(
                    colorStart = Color(0xFFff4000),
                    colorEnd = Color(0xFFffc6b3),
                )
            ),
            Pair(
                10, Gradient(
                    colorStart = Color(0xFFff0040),
                    colorEnd = Color(0xFFffb3c6),
                )
            ),
            Pair(
                11, Gradient(
                    colorStart = Color(0xFFff0080),
                    colorEnd = Color(0xFFffb3d9),
                )
            ),
            Pair(
                12, Gradient(
                    colorStart = Color(0xFFff00bf),
                    colorEnd = Color(0xFFffb3ec),
                )
            ),
            Pair(
                13, Gradient(
                    colorStart = Color(0xFFbf00ff),
                    colorEnd = Color(0xFFd9b3ff),
                )
            ),
            Pair(
                14, Gradient(
                    colorStart = Color(0xFF0080ff),
                    colorEnd = Color(0xFFb3d9ff),
                )
            ),
            Pair(
                15, Gradient(
                    colorStart = Color(0xFF0040ff),
                    colorEnd = Color(0xFFb3c6ff),
                )
            ),
            Pair(
                16, Gradient(
                    colorStart = Color(0xFF0000ff),
                    colorEnd = Color(0xFFb3b3ff),
                )
            ),
            Pair(
                17, Gradient(
                    colorStart = Color(0xFF8000ff),
                    colorEnd = Color(0xFFc6b3ff),
                )
            )
        )
    )

}