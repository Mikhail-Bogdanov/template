package com.evo.topbar

import com.evo.di.EvoModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind

class TopBarModule : EvoModule {

    override fun Module.initialize() {
        factoryOf(::TopBarImpl) bind TopBar::class
    }
}