package com.evo.easteregg

import com.evo.di.EvoModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind

class EasterEggModule : EvoModule {

    override fun Module.initialize() {
        factoryOf(::EasterEggImpl) bind EasterEgg::class
    }
}