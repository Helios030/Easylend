package com.easy.lend.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy.lend.ext.toast
import com.easy.lend.model.BaseModel
import com.easy.lend.net.ApiServices
import com.easy.lend.net.HttpManager
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
                toast(it.msg)

            }
        }.onFailure {
            it.printStackTrace()
            if (it is HttpException) {
                toast(it.message)
            }

            isLoading.value = false
            error(it)
        }
    }

}