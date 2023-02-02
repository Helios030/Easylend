package com.loanspeed.speed.ui.comm

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun Width(width: Dp) {
    Spacer(modifier = Modifier.width(width))
}

@Composable
fun Height(height: Dp) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun ColumnScope.Weight(weight: Float = 1f, fill: Boolean = true) {
    Spacer(modifier = Modifier.weight(weight, fill))
}

@Composable
fun RowScope.Weight(weight: Float = 1f, fill: Boolean = true) {
    Spacer(modifier = Modifier.weight(weight, fill))
}