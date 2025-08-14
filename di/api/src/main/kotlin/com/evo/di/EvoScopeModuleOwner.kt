package com.evo.di

import org.koin.core.component.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

abstract class EvoScopeModuleOwner : KoinComponent {

    protected val module = module { loadScopeModule() }

    init {
        loadKoinModules(module)
    }

    protected open fun Module.loadScopeModule() {}

    protected fun unloadScopeModule() {
        unloadKoinModules(module)
    }

}
