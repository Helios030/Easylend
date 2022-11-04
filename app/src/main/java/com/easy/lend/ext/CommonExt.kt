package com.easy.lend.ext

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.widget.Toast
import com.easy.lend.BuildConfig
import com.easy.lend.CharmApplication
import com.easy.lend.util.DeviceUtils
import com.easy.lend.util.Slog
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody


val context = CharmApplication.instance

fun toast(resId: Int) {
    Toast.makeText(context, str(resId), Toast.LENGTH_LONG).show()
}

fun toast(msg: String?) {
    if (!msg.isNullOrEmpty())
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}

fun Int.show() {
    try {
        str(this).show()
    } catch (e: Exception) {
        e.printStackTrace()
    }

}

fun String.show() {

    try {
        if (this.isNotEmpty())
            Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
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





