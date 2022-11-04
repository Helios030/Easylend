package com.easy.lend.ui.comm

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easy.lend.ui.theme.colorFFD1AA80
import com.easy.lend.ui.theme.colorOnPrimary

@Composable
fun RowText(
    margin: PaddingValues,
    text: String,
    value: String
) {
    Box(
        modifier = Modifier
            .padding(margin)
            .fillMaxWidth()
            .height(44.dp),
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = colorOnPrimary,
                fontSize = 14.sp,
            ),
            modifier = Modifier.align(Alignment.CenterStart)
        )
        Text(
            text = value,
            style = TextStyle(
                color = colorFFD1AA80,
                fontSize = 14.sp,
            ),
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}