package com.azhel.onthespot

import android.app.Application
import com.azhel.onthespot.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OnTheSpotApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@OnTheSpotApplication)
            modules(appModule)
        }
    }
}
