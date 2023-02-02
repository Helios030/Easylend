package com.loanspeed.speed.util.workManager

import android.content.Context
import android.os.Build
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.loanspeed.speed.BuildConfig
import com.loanspeed.speed.CharmApplication
import com.loanspeed.speed.R
import com.loanspeed.speed.ext.createBody
import com.loanspeed.speed.ext.str
import com.loanspeed.speed.net.ApiServices
import com.loanspeed.speed.net.HttpManager
import com.loanspeed.speed.util.AppUtil
import com.loanspeed.speed.util.DeviceUtils
import com.loanspeed.speed.util.DeviceUtils.checkEmulator
import com.loanspeed.speed.util.DeviceUtils.getDeviceHeight
import com.loanspeed.speed.util.DeviceUtils.getDeviceWidth
import com.loanspeed.speed.util.DeviceUtils.getMobileDbm
import com.loanspeed.speed.util.DeviceUtils.getNetWorkType
import com.loanspeed.speed.util.DeviceUtils.getRAMMemoryAvailable
import com.loanspeed.speed.util.DeviceUtils.getWifiInfo
import com.loanspeed.speed.util.DeviceUtils.isRoot
import com.loanspeed.speed.util.DeviceUtils.queryWithStorageManager
import com.loanspeed.speed.util.Slog
import com.loanspeed.speed.util.SpRepository

import com.loanspeed.speed.util.DeviceUtils.getUUID


/**
 * 设备信息收集
 */
class PhoneStateWM(private val context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    private val mApiService by lazy { HttpManager.create(ApiServices::class.java) }


    override suspend fun doWork(): Result {
        try {
            val map = HashMap<String, Any>()
            val mContext = CharmApplication.instance
            val wiFiBean = mContext.getWifiInfo()
//            手机号码
            map["phone"] = BuildConfig.AREA_CODE + SpRepository.phone
//            设备抓板
            map["phone_brand"] = Build.BRAND
//            设备类型
            map["phone_model"] = Build.MODEL
            //设备ID
            map["device_id"] = getUUID()
            //系统版本
            map["release"] = Build.VERSION.RELEASE
            //SDK版本
            map["sdk_version"] = Build.VERSION.SDK_INT
            //设备名称
            map["device_name"] = Build.DEVICE
            map["device_width"] = mContext.getDeviceWidth()//屏幕宽度
            map["device_height"] = mContext.getDeviceHeight()//屏幕高度
            map["ram_total_size"] = DeviceUtils.getRAMMemoryTotal()
            map["ram_usable_size"] = mContext.getRAMMemoryAvailable()
            map["memory_card_size"] = DeviceUtils.getSDCardMemoryTotal()
            map["memory_card_size_use"] = DeviceUtils.getSDCardMemoryAvailable()
            map["internal_storage_usable"] = DeviceUtils.getROMMemoryAvailable()
            map["internal_storage_used"] = mContext.queryWithStorageManager().split("-")[1]
            map["internal_storage_total"] = mContext.queryWithStorageManager().split("-")[0]
            map["language"] = DeviceUtils.getDeviceDefaultLanguage()
            map["imsi"] = SpRepository.uuid
            map["imei"] = SpRepository.uuid
            map["network_type"] = mContext.getNetWorkType()
            map["time_zone_id"] = DeviceUtils.getTimeZoneID()
            map["locale_iso_3_country"] = DeviceUtils.getMobLocalCountry()
            map["simulator"] = mContext.checkEmulator()
            map["dbm"] = mContext.getMobileDbm()
            map["ip"] = wiFiBean.ip
            map["mac"] = AppUtil.adresseMAC
            map["current_wifi_bssid"] = wiFiBean.bssid
            map["current_wifi_name"] = wiFiBean.wifiName
            map["current_wifi_ssid"] = wiFiBean.ssid
            map["current_wifi_mac"] = wiFiBean.mac
            map["latitude"] = SpRepository.location_lat
            map["longitude"] = SpRepository.location_lon
            map["phone_token"] = ""
            map["app"] = str(R.string.app_name)
            map["sub_app"] = str(R.string.app_name) + "_android"
            map["password"] = ""
            map["new_password"] = ""
            map["ov"] = Build.VERSION.RELEASE
            map["place"] = "google"
            map["sub_place_code"] = ""
            map["other_info"] = ""
            map["passport"] = ""
            map["oaid"] = SpRepository.oaid
            map["root"] = mContext.isRoot()

            mApiService.upPhoneState(map.createBody())
            return Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Slog.e("===>  设备信息上传出错 $e ")
            return Result.failure()
        }

    }

}