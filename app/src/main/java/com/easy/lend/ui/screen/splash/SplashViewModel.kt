package com.easy.lend.ui.screen.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.easy.lend.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel  @Inject constructor() : BaseViewModel()  {


    var isFinish by mutableStateOf(false)

    suspend fun initSplash() {
        delay(2000)
        isFinish = true
    }

    init {
        viewModelScope.launch {
            initSplash()
        }
    }



}