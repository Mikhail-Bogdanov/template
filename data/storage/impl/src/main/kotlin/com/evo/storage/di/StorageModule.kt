package com.evo.storage.di

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.evo.di.EvoModule
import com.evo.storage.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind

class StorageModule : EvoModule {

    override fun Module.initialize() {
        single {
            PreferenceDataStoreFactory.create {
                androidContext().preferencesDataStoreFile("evo_datastore")
            }
        }
        factory {
            StorageHandlerImpl<Any>(evoDataStore = get())
        } bind StorageHandler::class
        factoryOf(::ThemeHandlerImpl) bind ThemeHandler::class
        factoryOf(::LocaleHandlerImpl) bind LocaleHandler::class
    }
}
