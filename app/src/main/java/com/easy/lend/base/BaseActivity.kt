package com.easy.lend.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider


/**
 * @param isFullHeight 是否是全屏，false -> add a status bar view
 */

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