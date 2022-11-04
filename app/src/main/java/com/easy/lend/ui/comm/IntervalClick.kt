package com.idsix.composewar.ui.common

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalContext

const val intervalTime = 500L

@Composable
inline fun intervalClick(crossinline onClick: () -> Unit): () -> Unit {
    var lastClickTime = remember { 0L }
    val context = LocalContext.current
    return {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime >= intervalTime) {
            onClick()
            lastClickTime = currentTime
        }
    }
}

@SuppressLint("UnnecessaryComposedModifier")
inline fun Modifier.intervalClick(crossinline onClick: () -> Unit) = composed {
    var lastClickTime = remember { 0L }
    clickable(indication = null, interactionSource = MutableInteractionSource()) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime >= intervalTime) {
            onClick()
            lastClickTime = currentTime
        }
    }
}