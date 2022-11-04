package com.easy.lend.ui.comm

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StatusBar() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(25.dp
//                SizeUtils.px2dp(
//                    BarUtils
//                        .getStatusBarHeight()
//                        .toFloat()
//                ).dp
            )
    )
}