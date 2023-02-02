package com.loanspeed.speed.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import com.loanspeed.speed.util.slog
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.XXPermissions


inline fun <reified T : Activity> Context.launch(block: Intent.() -> Unit) {
    val intent = Intent(this, T::class.java)
    block(intent)
    startActivity(intent)
}




inline fun <reified T : Activity> Context.launchNewTask(block: Intent.() -> Unit) {
    val intent = Intent(this, T::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    block(intent)
    startActivity(intent)
}





fun Activity.makeCall(phoneNumber:String){
    val intent = Intent(Intent.ACTION_DIAL)
    intent.data = Uri.parse("tel:$phoneNumber")
    if (intent.resolveActivity(this.packageManager) != null) {
        this.startActivity(intent)
    }

}


fun Activity.showImageForUri(uri:String, view: ImageView){

    if(uri.isEmpty()){
        return
    }
//    Glide.with(this).load(uri).thumbnail(Glide.with(this).load(R.drawable.load)).fitCenter().into(view);
}











inline fun Activity.requestPermission(
    permission: Array<String>,
    crossinline agreeBlock: () -> Unit
) {
    if (XXPermissions.isGranted(this, permission)) {
        agreeBlock()
    } else {
        XXPermissions.with(this)
            .permission(permission)
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>, all: Boolean) {
                    if (all) agreeBlock() else permissions.slog("获取部分权限成功，但部分权限未正常授予")
                }
                override fun onDenied(permissions: MutableList<String>, never: Boolean) {
                    if (never) {
                        permissions.slog("被永久拒绝授权")
                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
                        XXPermissions.startPermissionActivity(context, permissions)
                    } else {
                        permissions.slog("为获得权限 ")
                    }
                }
            })
    }

}
