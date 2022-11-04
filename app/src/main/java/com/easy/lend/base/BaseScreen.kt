package com.easy.lend.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.LifecycleObserver
import com.idsix.composewar.ui.common.intervalClick

interface IScreen : LifecycleObserver {
    @Composable
    fun Create()
}

abstract class BaseScreen : IScreen {
    fun Modifier.autoHideSoftware() = composed {
        val localFocusManager = LocalFocusManager.current
        intervalClick { localFocusManager.clearFocus(force = true) }
    }
}