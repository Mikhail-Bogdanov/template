package com.evo.team

/* [IMPORT] */
import android.app.Application
import com.evo.database.di.DatabaseModule
import com.evo.domain.di.DomainModule
import com.evo.entrypoint.AppEntryPointModule
import com.evo.initialscreen.InitialScreenModule
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
        InitialScreenModule.module,
        AppEntryPointModule(),
        DatabaseModule(),
        StorageModule(),
        NetworkModule(),
        DomainModule(),
    )
}
