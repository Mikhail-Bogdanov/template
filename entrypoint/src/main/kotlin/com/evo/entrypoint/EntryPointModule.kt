package com.evo.entrypoint

import com.evo.di.EvoModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module

class EntryPointModule : EvoModule {

    override fun Module.initialize() {
        viewModelOf(::EntryPointViewModel)

        single {
            androidContext().resources.configuration
        }
    }
}
