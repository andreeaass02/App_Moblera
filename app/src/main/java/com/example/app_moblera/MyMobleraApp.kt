package com.example.app_moblera

import android.app.Application
import com.example.app_moblera.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyMobleraApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyMobleraApp)
            modules(appModule)
        }
    }

}