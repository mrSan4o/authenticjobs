package com.san4o.just4fun.authenticjobs

import android.app.Application
import com.san4o.just4fun.authenticjobs.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@App)
            modules(listOf(appModule))
        }

        Timber.d("App start")
    }
}