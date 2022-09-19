package com.vlibrovs.twentyfortyeight.ui.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.vlibrovs.twentyfortyeight.data.model.theme.Theme

@Composable
fun SecondaryBackgroundBox(
    modifier: Modifier = Modifier,
    theme: Theme,
    cornerRadius: Dp,
    outerPaddingValues: PaddingValues = PaddingValues(0.dp),
    content: @Composable () -> Unit
) {
    Box(modifier = modifier.padding(outerPaddingValues).background(
        color = theme.secondaryBackgroundColor,
        shape = RoundedCornerShape(cornerRadius)
    )) {
        content()
    }
}