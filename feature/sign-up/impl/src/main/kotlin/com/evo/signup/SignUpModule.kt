package com.evo.signup

import com.evo.di.EvoModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind

class SignUpModule : EvoModule() {

    override fun Module.initialize() {
        factoryOf(::ScreenModel)
        factoryOf(::SignUpImpl) bind SignUp::class
    }
}
