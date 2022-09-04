package com.vlibrovs.twentyfortyeight.ui.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun ColorCircle(
    fillColor: Color,
    outlineColor: Color,
    outlineWidth: Dp,
    size: Dp,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .size(size)
            .border(width = outlineWidth, color = outlineColor, shape = CircleShape)
            .padding(outlineWidth)
            .background(
                shape = CircleShape,
                color = fillColor
            )
            .clickable {

            }
    )
}