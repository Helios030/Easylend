package com.loanspeed.speed.ui.screen.login

import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.loanspeed.speed.BuildConfig
import com.loanspeed.speed.R
import com.loanspeed.speed.base.BaseViewModel
import com.loanspeed.speed.ext.createBody
import com.loanspeed.speed.ext.show
import com.loanspeed.speed.ext.str
import com.loanspeed.speed.model.LoginModel
import com.loanspeed.speed.util.Slog
import com.loanspeed.speed.util.SpRepository
import com.loanspeed.speed.util.slog
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
            (R.string.please_enter_phone).show()
            return
        }
        val dataMap = HashMap<String, Any>()
        dataMap["phone"] = BuildConfig.AREA_CODE + phone
        request({
            mApiService.getVerificationCode(dataMap.createBody())
        }, {
            changeCodeStatus()
        })
    }

    fun sendVoiceCode(phone: String) {

        val dataMap = HashMap<String, Any>()
        dataMap["phone"] = BuildConfig.AREA_CODE + phone
        request({
            mApiService.getVoiceCode(dataMap.createBody())
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
            SpRepository.phone=phone

            liveLoginBean.value = this

        })


    }


    fun upInstallReferrer(map: HashMap<String, Any>) {
        request({
            mApiService.uploadAppSource(map.createBody())
        }, {
            Slog.d("===>  来源上传  ")
        })

    }


}