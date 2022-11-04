package com.easy.lend.ui.comm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.easy.lend.ui.theme.colorFFEFEFEF

@Composable
fun HorizontalLine(margin: PaddingValues = PaddingValues()) {
    Box(
        modifier = Modifier
            .padding(margin)
            .fillMaxWidth()
            .height(1.dp)
            .background(colorFFEFEFEF)
    )
}

@Composable
fun VerticalLine(margin: PaddingValues = PaddingValues()) {
    Box(
        modifier = Modifier
            .padding(margin)
            .width(1.dp)
            .fillMaxHeight()
            .background(colorFFEFEFEF)
    )
}