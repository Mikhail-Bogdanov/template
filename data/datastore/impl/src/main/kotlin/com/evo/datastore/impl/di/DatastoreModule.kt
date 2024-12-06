package com.evo.datastore.impl.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.evo.datastore.api.EvoDatastoreHandler
import com.evo.datastore.impl.EvoDatastoreHandlerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

object DatastoreModule {
    private val module = module {
        single { androidContext().datastore }
        factory {
            EvoDatastoreHandlerImpl<Any>(evoDataStore = get())
        } bind EvoDatastoreHandler::class
    }

    private const val DATASTORE_NAME = "evo_datastore"

    private val Context.datastore by preferencesDataStore(DATASTORE_NAME)

    operator fun invoke() = module
}