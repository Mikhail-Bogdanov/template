package com.evo.team

/* [IMPORT] */
import android.app.Application
import com.evo.database.di.DatabaseModule
import com.evo.mainpage.di.MainPageModule
import com.evo.navigation.di.NavigationModule
import com.evo.network.di.NetworkModule
import com.evo.storage.di.StorageModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App.applicationContext)
            initializeAllModules()
        }
    }

    private fun KoinApplication.initializeAllModules() = modules(
        /* [MODULES] */
        NavigationModule(),
        DatabaseModule(),
        StorageModule(),
        NetworkModule(),
        MainPageModule(),
    )
}