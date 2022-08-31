package com.vlibrovs.twentyfortyeight.ui.common.composables

import android.content.ClipDescription
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.vlibrovs.twentyfortyeight.R
import com.vlibrovs.twentyfortyeight.data.model.Theme
import com.vlibrovs.twentyfortyeight.ui.common.fonts.Fonts

@Composable
fun Button(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit,
    onClick: () -> Unit,
    theme: Theme
) {
    Box(
        modifier = modifier
            .background(color = theme.buttonColor, shape = RoundedCornerShape(50.dp))
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            textAlign = TextAlign.Center,
            fontFamily = Fonts.Poppins,
            fontWeight = FontWeight.SemiBold,
            color = theme.textColor,
            fontSize = fontSize
        )
    }
}

@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    contentDescription: String,
    iconSize: Dp,
    onClick: () -> Unit,
    theme: Theme
) {
    Box(
        modifier = modifier
            .background(color = theme.buttonColor, shape = RoundedCornerShape(50.dp))
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(iconSize),
            painter = icon,
            tint = theme.textColor,
            contentDescription = contentDescription
        )
    }
}