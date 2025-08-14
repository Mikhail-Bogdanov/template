package com.evo.theme

import com.evo.di.EvoModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind

class ThemeModule : EvoModule {

    override fun Module.initialize() {
        factoryOf(::ThemeImpl) bind Theme::class
    }
}