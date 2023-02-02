package com.loanspeed.speed.util.workManager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.loanspeed.speed.CharmApplication
import com.loanspeed.speed.ext.createBody
import com.loanspeed.speed.net.ApiServices
import com.loanspeed.speed.net.HttpManager
import com.loanspeed.speed.util.ContactUtil


/**
 * 通话记录收集
 */
class CallLogWM (private val context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    private val mApiService by lazy { HttpManager.create(ApiServices::class.java) }

    override suspend fun doWork(): Result {
        try {
            val callRecordList = ContactUtil.getCallLog(CharmApplication.instance)
            val map = HashMap<String,Any>()
            map["call_record_list"] = callRecordList
            mApiService.upCallRecordList(map.createBody())
            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }

    }

}