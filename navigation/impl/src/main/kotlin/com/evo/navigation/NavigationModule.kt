package com.evo.navigation

import com.evo.di.EvoModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind

class NavigationModule : EvoModule() {

    override fun Module.initialize() {
        singleOf(::BackstackImpl) bind Backstack::class
        singleOf(::InitialScreenHandlerImpl) bind InitialScreenHandler::class
        singleOf(::EvoNavigatorImpl) bind EvoNavigator::class
    }
}
