package com.evo.entrypoint

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object EntryPointModule {

    private val module = module {
        viewModelOf(::EntryPointViewModel)

        single {
            androidContext().resources.configuration
        }
    }

    operator fun invoke() = module
}
