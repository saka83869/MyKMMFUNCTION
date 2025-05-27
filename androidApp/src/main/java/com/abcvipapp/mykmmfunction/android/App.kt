package com.abcvipapp.mykmmfunction.android

import android.app.Application
import com.abcvipapp.mykmmfunction.di.initKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        _root_ide_package_.com.abcvipapp.mykmmfunction.di.enableLog = BuildConfig.DEBUG
    }
}
