package com.evo.login

import com.evo.di.EvoModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind

class LoginModule : EvoModule {

    override fun Module.initialize() {
        factoryOf(::LoginImpl) bind Login::class
    }
}