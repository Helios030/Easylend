package com.loanspeed.speed.ui.screen.index

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.loanspeed.speed.base.BaseViewModel
import com.loanspeed.speed.ext.createBody
import com.loanspeed.speed.model.UserInfoModel
import com.loanspeed.speed.util.DeviceUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IndexViewModel @Inject constructor() : BaseViewModel() {


    val userInfoLiveData: LiveData<UserInfoModel>
        get() = liveUserInfo


    val liveUserInfo = MutableLiveData<UserInfoModel>()

    val isCancel = MutableLiveData<Boolean>()

    var isInit = true



    fun getUserInfo() {
        val dataMap = HashMap<String, Any>()
        dataMap["isRoot"] = DeviceUtils.isSuEnableRoot()
        request({
            mApiService.getUserInfo(dataMap.createBody())
        }, {
            liveUserInfo.value = this



        })
    }


    fun cancelOrder(orderNo: String) {
        val dataMap = HashMap<String, Any>()
        dataMap["order_no"] = orderNo
        request({
            mApiService.orderCancel(dataMap.createBody())
        }, {
            isCancel.value = this

        })
    }


}