package com.evo.team

import android.app.Application
import com.evo.di.ModulesInitializer
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App.applicationContext)
            with(ModulesInitializer) {
                start()
            }
        }
    }
}
