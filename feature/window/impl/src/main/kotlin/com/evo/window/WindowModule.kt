package com.evo.window

import com.evo.di.EvoModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind

class WindowModule : EvoModule() {

    override fun Module.initialize() {
        singleOf(::EvoWindowHandlerImpl) bind EvoWindowHandler::class
        singleOf(::EvoWindowImpl) bind EvoWindow::class
    }
}
