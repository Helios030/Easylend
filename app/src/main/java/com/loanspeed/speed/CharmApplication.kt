package com.loanspeed.speed

import android.app.Application
import com.loanspeed.speed.util.Slog
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