package com.evo.update

import com.evo.di.EvoModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind

class UpdateModule : EvoModule() {

    override fun Module.initialize() {
        factoryOf(::UpdateImpl) bind Update::class
    }
}