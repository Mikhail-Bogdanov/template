package com.evo.startscreen

import com.evo.di.EvoModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind

class StartScreenModule : EvoModule() {

    override fun Module.initialize() {
        factoryOf(::StartScreenImpl) bind StartScreen::class
        factoryOf(::ScreenModel)
    }
}