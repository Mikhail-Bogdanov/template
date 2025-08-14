package com.evo.permission

import com.evo.di.EvoModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind

class PermissionModule : EvoModule {

    override fun Module.initialize() {
        factoryOf(::PermissionImpl) bind Permission::class
    }
}