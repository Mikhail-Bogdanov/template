package com.evo.di

/* [IMPORT] */
import com.evo.database.di.DatabaseModule
import com.evo.entrypoint.EntryPointModule
import com.evo.login.LogInModule
import com.evo.network.di.NetworkModule
import com.evo.settings.SettingsModule
import com.evo.signup.SignUpModule
import com.evo.storage.di.StorageModule
import org.koin.core.KoinApplication
import org.koin.dsl.module

object ModulesInitializer {

    fun KoinApplication.start() {
        val appModules = listOf(
            /* [MODULES] */
            SignUpModule(),
            LogInModule(),
            SettingsModule(),
            EntryPointModule(),
            DatabaseModule(),
            StorageModule(),
            NetworkModule(),
        ).map { evoModule ->
            module {
                evoModule.apply { initialize() }
            }
        }

        modules(appModules)
    }
}
