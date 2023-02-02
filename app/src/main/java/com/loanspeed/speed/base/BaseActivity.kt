package com.loanspeed.speed.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider


abstract class BaseActivity<VM : BaseViewModel>(
    private val clazz: Class<VM>
) : ComponentActivity() {

    val vm by lazy { ViewModelProvider(this)[clazz] }
    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initArgument()
        initData()
    }

    open fun initArgument() {}
    open fun initData() {}


}