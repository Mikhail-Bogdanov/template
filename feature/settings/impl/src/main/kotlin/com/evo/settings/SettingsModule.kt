package com.evo.settings

import com.evo.di.EvoModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind

class SettingsModule : EvoModule {

    override fun Module.initialize() {
        factoryOf(::SettingsImpl) bind Settings::class
    }
}