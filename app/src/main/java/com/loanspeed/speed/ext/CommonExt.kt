package com.loanspeed.speed.ext

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.loanspeed.speed.BuildConfig
import com.loanspeed.speed.CharmApplication
import com.loanspeed.speed.util.DeviceUtils
import com.loanspeed.speed.util.Slog
import com.google.gson.Gson
import com.loanspeed.speed.model.MenuItemModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody


val context = CharmApplication.instance



fun openUri(uri: String) {
   val intent= Intent(Intent.ACTION_VIEW, Uri.parse(uri))
    intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(context, intent, null)

}




fun String.show() {

    try {
        if (this.isNotEmpty())
            Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Any?.show(){
    try {
        "$this".show()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun str(resId: Int): String {
    return context.getString(resId)
}

fun color(resId: Int): Int {
    return context.getColor(resId)
}


var lastClickTime = 0L
fun View.onClickListener(delay: Long = 500L, block: View.() -> Unit) {
    setOnClickListener {
        val currentTime = System.currentTimeMillis()
        if (lastClickTime != 0L && (currentTime - lastClickTime < delay)) {
            return@setOnClickListener
        }
        lastClickTime = currentTime
        block()
    }
}


fun HashMap<String, Any>.createBody(): RequestBody {
    val json = Gson().toJson(this.createCommonParams())
    Slog.d(" 当前API参数  json  $json")
    return RequestBody.create(
        "application/json;charset=UTF-8".toMediaTypeOrNull(),
        json
    )
}

fun HashMap<String, Any>.createCommonParams(): HashMap<String, Any> {
    this.apply {
        this["place"] = "google"
        this["sub_place_code"] = ""
        this["other_info"] = ""
        this["phone_brand"] = Build.BRAND
        this["phone_model"] = Build.MODEL
        this["device_id"] = DeviceUtils.getUUID()
        this["pck_name"] = BuildConfig.APPLICATION_ID
        this["invitationCode"] = ""
    }
    return this
}

fun HashMap<String, String>.associateMap(): Map<String, String> {
    return this.entries.associateBy({ it.value }) { it.key }
}


fun HashMap<String, String>.getHashMapByValue(value: String): String? {
    var key: String? = null
    val set: Set<Map.Entry<String, String>> = this.entries
    for ((key1, value1) in set) {
        if (value1 == value) {
            key = key1
            break
        }
    }
    return key
}

// map转pop windows 数据类
fun  Map<String, String?>.map2MenuItem(): List<MenuItemModel> = this.map { MenuItemModel(it.value!!, false, menuCode = it.key) }

fun  List<String>.str2MenuItem(): List<MenuItemModel> = this.map { MenuItemModel(it, false) }



fun Any.toMap(): HashMap<String, Any> {
    val map: HashMap<String, Any> = HashMap()
    for (field in this.javaClass.declaredFields) {

        try {
            val flag = field.isAccessible
            field.isAccessible = true
            val o = field[this]
            map[field.name] = o
            field.isAccessible = flag
        } catch (e: Exception) {
            e.printStackTrace()
            continue
        }


    }
    return map
}


fun isNetAvailable(): Boolean {
    var isNetUsable = false
    val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities: NetworkCapabilities? =
        manager.getNetworkCapabilities(manager.activeNetwork)
    if (networkCapabilities != null) {
        isNetUsable =
            networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }
    return isNetUsable
}



fun String.getFileName(): String? {
    val start = this.lastIndexOf("/")
    val end = this.lastIndexOf(".")
    return if (start != -1 && end != -1) {
        this.substring(start + 1, end)
    } else {
        null
    }
}


fun cvtLocTag(locTag: String, locRef: String): Double {
    // 直接获取到的位置信息需要解析一下才能使用
    // 33/1,26/1,465/10000
    // 度分秒以逗号隔开，且以除号表示，这样做的好处是以整形存储了浮点型数据
    val loc_deg = locTag.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
    val loc_min = locTag.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
    val loc_sec = locTag.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[2]
    val loc_Deg = loc_deg.split("/".toRegex()).dropLastWhile { it.isEmpty() }
        .toTypedArray()[0].toDouble() / 1.0
    val loc_Min = loc_min.split("/".toRegex()).dropLastWhile { it.isEmpty() }
        .toTypedArray()[0].toDouble() / 1.0
    val loc_Sec = loc_sec.split("/".toRegex()).dropLastWhile { it.isEmpty() }
        .toTypedArray()[0].toDouble() / 10000.0
    var loc = loc_Deg + loc_Min / 60.0 + loc_Sec / 3600.0
    // 在计算中南纬和西经都为负数，所以增加符号
    if (locRef.contains("S") || locRef.contains("W")) {
        loc = -1 * loc
    }
    return loc
}










