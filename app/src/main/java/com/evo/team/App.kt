package com.evo.team

import android.app.Application
import com.evo.appdata.di.AppDataModule
import com.evo.database.di.DatabaseModule
import com.evo.datastore.di.DatastoreModule
import com.evo.mainpage.di.MainPageModule
import com.evo.navigation.di.NavigationModule
import com.evo.network.di.NetworkModule
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
                DatastoreModule(),
                NetworkModule(),
                AppDataModule(),
                MainPageModule(),
            )
        }
    }
}