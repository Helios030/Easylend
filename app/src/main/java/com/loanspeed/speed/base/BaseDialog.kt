package com.loanspeed.speed.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.loanspeed.speed.ui.common.intervalClick

@Composable
fun BaseDialog(
    onDismissRequest: () -> Unit, content: @Composable BoxScope.() -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .intervalClick(onClick = onDismissRequest),
            contentAlignment = Alignment.Center,
            content = content
        )
    }
}