package com.evo.storage.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.evo.storage.EvoDatastoreHandler
import com.evo.storage.EvoDatastoreHandlerImpl
import com.evo.storage.locale.LocaleHandler
import com.evo.storage.locale.LocaleHandlerImpl
import com.evo.storage.theme.ThemeHandler
import com.evo.storage.theme.ThemeHandlerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

object StorageModule {
    private val module = module {
        single { androidContext().datastore }
        factory {
            EvoDatastoreHandlerImpl<Any>(evoDataStore = get())
        } bind EvoDatastoreHandler::class
        factoryOf(::ThemeHandlerImpl) bind ThemeHandler::class
        factoryOf(::LocaleHandlerImpl) bind LocaleHandler::class
    }

    private const val DATASTORE_NAME = "evo_datastore"

    private val Context.datastore by preferencesDataStore(DATASTORE_NAME)

    operator fun invoke() = module
}