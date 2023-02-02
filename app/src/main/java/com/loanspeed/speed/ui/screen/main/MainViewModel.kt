package com.loanspeed.speed.ui.screen.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.loanspeed.speed.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {

    var liveStr = MutableLiveData<String>()
    var isSplash by mutableStateOf(false)


    fun getData() {

        liveStr.value = "延时数据"


    }

    suspend fun initSplash() {
        delay(2000)
        isSplash = true
    }

    init {
        viewModelScope.launch {
            initSplash()
        }


    }


}