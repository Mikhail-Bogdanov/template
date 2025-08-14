package com.evo.locale

import com.evo.di.EvoModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind

class LocaleModule : EvoModule {

    override fun Module.initialize() {
        factoryOf(::LocaleImpl) bind Locale::class
    }
}