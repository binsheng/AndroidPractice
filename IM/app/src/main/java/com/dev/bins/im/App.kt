package com.dev.bins.im

import android.app.Application
import io.rong.imkit.RongIM

/**
 * Created by bin on 19/05/2017.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        RongIM.init(this)
    }

}