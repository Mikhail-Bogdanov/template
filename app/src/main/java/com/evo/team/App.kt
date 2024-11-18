package com.evo.team

import android.app.Application
import com.evo.database.impl.di.DatabaseModule
import com.evo.navigation.impl.di.NavigationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App.applicationContext)
            modules(
                NavigationModule(),
                DatabaseModule(),
            )
        }
    }
}