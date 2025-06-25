package com.evo.storage.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.evo.storage.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

object StorageModule {

    private val module = module {
        single { androidContext().datastore }
        factory {
            StorageHandlerImpl<Any>(evoDataStore = get())
        } bind StorageHandler::class
        factoryOf(::ThemeHandlerImpl) bind ThemeHandler::class
        factoryOf(::LocaleHandlerImpl) bind LocaleHandler::class
    }

    private const val DATASTORE_NAME = "evo_datastore"

    private val Context.datastore by preferencesDataStore(DATASTORE_NAME)

    operator fun invoke() = module
}
