package com.loanspeed.speed.base

import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loanspeed.speed.ext.initData
import com.loanspeed.speed.ext.show
import com.loanspeed.speed.model.BaseModel
import com.loanspeed.speed.net.ApiServices
import com.loanspeed.speed.net.HttpManager
import kotlinx.coroutines.launch
import retrofit2.HttpException

open class BaseViewModel: ViewModel() {

     val isLoading = MutableLiveData(false)

    protected val mApiService by lazy { HttpManager.create(ApiServices::class.java) }





    protected fun <T> request(
        block: suspend () -> BaseModel<T>,
        data: T.() -> Unit,
        error: (Throwable.() -> Unit) = {},
        isShowLoading: Boolean = true
    ) = viewModelScope.launch {



        runCatching {
            isLoading.value = isShowLoading
            block()
        }.onSuccess {
            isLoading.value = false
            if (it.success) {
                it.ret?.let { data -> data(data) }
            } else {

            }
        }.onFailure {
            it.printStackTrace()
            if (it is HttpException) {
                it.message().show()
            }

            isLoading.value = false
            error(it)
        }
    }

}