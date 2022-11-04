package com.easy.lend.ui.screen.main

import android.Manifest
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.easy.lend.R
import com.easy.lend.base.BaseActivity
import com.easy.lend.ext.requestPermission
import com.easy.lend.ext.show
import com.easy.lend.rout.RoutPath
import com.easy.lend.ui.screen.index.IndexScreen
import com.easy.lend.ui.screen.login.LoginScreen
import com.easy.lend.ui.screen.splash.SplashScreen
import com.easy.lend.ui.theme.ComposeLoanTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel>(MainViewModel::class.java) {
    private val mainViewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalAnimationApi::class)
    override fun initData() {
        super.initData()
        reqPermission()

        // 全屏
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {

            ComposeLoanTheme {
                val navController = rememberAnimatedNavController()
                AnimatedNavHost(
                    navController = navController,
                    startDestination = RoutPath.SPLASH,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colors.background),
                ) {

                    composable(RoutPath.INDEX) {
                        IndexScreen(navController)
                    }
                    composable(RoutPath.LOGIN) {
                        LoginScreen(navController)
                    }
                    composable(RoutPath.SPLASH) {
                        SplashScreen(navController)
                    }




                }
            }
        }
    }

    private fun reqPermission() {
        requestPermission(
            arrayOf(
                Manifest.permission.READ_SMS,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        ) {
            " 权限获取成功".show()
        }
    }


    // 定义一个变量，来标识是否退出
    private var isExit = false
    private var mHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            isExit = false
        }
    }

    override fun onBackPressed() {


        if (!isExit) {
            isExit = true
            R.string.tip_exit_app.show()
            mHandler.sendEmptyMessageDelayed(0, 2000)
        } else {
            finish()
        }
    }

}








