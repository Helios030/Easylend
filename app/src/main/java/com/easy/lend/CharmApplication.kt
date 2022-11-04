package com.easy.lend

import android.app.Application
import com.easy.lend.util.Slog
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class CharmApplication : Application() {
    companion object {
        lateinit var instance: CharmApplication
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        Slog.getSettings().setLogEnable(BuildConfig.DEBUG).setBorderEnable(
            BuildConfig.DEBUG)
    }


}