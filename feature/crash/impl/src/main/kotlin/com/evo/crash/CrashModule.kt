package com.evo.crash

import com.evo.di.EvoModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind

class CrashModule : EvoModule() {

    override fun Module.initialize() {
        factoryOf(::ScreenModel)
        factoryOf(::CrashImpl) bind Crash::class
    }
}
