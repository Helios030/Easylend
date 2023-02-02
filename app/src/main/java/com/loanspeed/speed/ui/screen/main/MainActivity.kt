package com.loanspeed.speed.ui.screen.main

import com.loanspeed.speed.R
import android.Manifest
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.loanspeed.speed.base.BaseActivity
import com.loanspeed.speed.ext.requestPermission
import com.loanspeed.speed.ext.show
import com.loanspeed.speed.rout.RoutPath
import com.loanspeed.speed.ui.screen.login.LoginScreen
import com.loanspeed.speed.ui.screen.splash.SplashScreen
import com.loanspeed.speed.ui.theme.ComposeLoanTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.loanspeed.speed.ui.screen.index.IndexScreen
import com.loanspeed.speed.ui.screen.user.first.DataFirstScreen
import com.loanspeed.speed.ui.screen.user.fourth.DataFourthScreen
import com.loanspeed.speed.ui.screen.user.second.DataSecondScreen
import com.loanspeed.speed.util.Slog

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel>(MainViewModel::class.java)  {

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
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colors.background
                ) {

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

                        composable(RoutPath.USER_DATA_FIRST) {
                            DataFirstScreen(navController)

                        }

                        composable(RoutPath.USER_DATA_SECOND) {
                            DataSecondScreen(navController)

                        }
                        composable(RoutPath.USER_DATA_FOURTH) {
                            DataFourthScreen(navController)

                        }



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








