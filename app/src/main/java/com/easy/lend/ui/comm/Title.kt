package com.easy.lend.ui.comm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easy.lend.base.BaseScreen
import com.easy.lend.ui.theme.colorFFDADADA
import com.easy.lend.ui.theme.colorOnPrimary

@Composable
fun BaseScreen.CenterTitle(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .autoHideSoftware(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StatusBar()
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = colorOnPrimary,
            modifier = Modifier.padding(vertical = 8.dp),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(color = colorFFDADADA.copy(alpha = 0.82f))
        )
        content()
    }
}