package com.easy.lend.ui.screen.login

import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.easy.lend.BuildConfig
import com.easy.lend.R
import com.easy.lend.base.BaseViewModel
import com.easy.lend.ext.createBody
import com.easy.lend.ext.str
import com.easy.lend.ext.toast
import com.easy.lend.model.LoginModel
import com.easy.lend.util.Slog
import com.easy.lend.util.SpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel() {
    val liveCode = MutableLiveData<Boolean>()
    val liveCodeStr = MutableLiveData<String>()
    val liveLoginBean = MutableLiveData<LoginModel>()
    fun sendCode(phone: String) {
        if (phone.isEmpty()) {
            toast(R.string.compose_loan_please_input_phone)
            return
        }
        val dataMap = HashMap<String, Any>()
        dataMap["phone"] = BuildConfig.AREA_CODE + phone
        request({
            Slog.d("发起请求    ")
            mApiService.getVerificationCode(dataMap.createBody())
        }, {
            Slog.d("返回结果  $this   ")
            changeCodeStatus()
        }, {
            Slog.d("访问错误  $this")
        })
    }

    fun sendVoiceCode(phone: String) {

        val dataMap = HashMap<String, Any>()
        dataMap["phone"] = BuildConfig.AREA_CODE + phone
        request({
            mApiService.getVoiceVerificationCode(dataMap.createBody())
        }, {
            changeCodeStatus()
        })
    }


    private fun changeCodeStatus() {
        viewModelScope.launch {
            liveCode.value = false
            for (i in 60 downTo 1) {
                liveCodeStr.value = "${i}s"
                delay(1000)
            }
            liveCode.value = true
            liveCodeStr.value = ""
        }
    }


    fun login(phone: String, code: String) {
        val dataMap = HashMap<String, Any>()
        dataMap["phone"] = BuildConfig.AREA_CODE + phone
        dataMap["valid_code"] = code
        dataMap["phone_token"] = ""
        dataMap["app"] = str(R.string.app_name)
        dataMap["sub_app"] = str(R.string.app_name) + "_android"
        dataMap["password"] = ""
        dataMap["new_password"] = ""
        dataMap["ov"] = Build.VERSION.RELEASE
        dataMap["place"] = "google"
        dataMap["sub_place_code"] = ""
        dataMap["other_info"] = ""
        dataMap["passport"] = ""
        request({
            mApiService.login(dataMap.createBody())
        }, {
            SpRepository.token = this.token
            liveLoginBean.value = this

        })


    }


    fun upInstallReferrer(map: HashMap<String, Any>) {
        request({
            mApiService.upInstallReferrer(map.createBody())
        }, {
            Slog.d("===>  来源上传  ")
        })

    }



}