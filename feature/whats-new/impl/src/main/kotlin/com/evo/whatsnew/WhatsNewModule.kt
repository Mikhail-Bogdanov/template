package com.evo.whatsnew

import com.evo.di.EvoModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind

class WhatsNewModule : EvoModule() {

    override fun Module.initialize() {
        factoryOf(::WhatsNewImpl) bind WhatsNew::class
        factoryOf(::ScreenModel)
    }
}