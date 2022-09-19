package com.vlibrovs.twentyfortyeight

import android.app.Application
import com.vlibrovs.twentyfortyeight.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GameApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GameApplication)
            modules(appModule)
        }
    }

}