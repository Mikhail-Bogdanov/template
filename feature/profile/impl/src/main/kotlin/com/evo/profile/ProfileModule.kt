package com.evo.profile

import com.evo.di.EvoModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind

class ProfileModule : EvoModule {

    override fun Module.initialize() {
        factoryOf(::ProfileImpl) bind Profile::class
    }
}