package com.evo.bottombar

import com.evo.di.EvoModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind

class BottomBarModule : EvoModule() {

    override fun Module.initialize() {
        factoryOf(::BottomBarImpl) bind BottomBar::class
    }
}
