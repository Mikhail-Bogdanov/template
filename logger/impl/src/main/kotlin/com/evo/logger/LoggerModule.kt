package com.evo.logger

import com.evo.di.EvoModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind

class LoggerModule : EvoModule {

    override fun Module.initialize() {
        factoryOf(::EvoLoggerImpl) bind EvoLogger::class
    }
}
