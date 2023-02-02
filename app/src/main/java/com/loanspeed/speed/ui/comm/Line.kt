package com.loanspeed.speed.ui.comm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.loanspeed.speed.ui.theme.colorFFEFEFEF
import com.loanspeed.speed.ui.theme.colorPrimary

@Composable
fun HorizontalLine(margin: PaddingValues = PaddingValues()) {
    Box(
        modifier = Modifier
            .padding(margin)
            .fillMaxWidth()
            .height(1.dp)
            .background(colorPrimary)
    )
}

@Composable
fun VerticalLine(margin: PaddingValues = PaddingValues()) {
    Box(
        modifier = Modifier
            .padding(margin)
            .width(1.dp)
            .fillMaxHeight()
            .background(colorPrimary)
    )
}