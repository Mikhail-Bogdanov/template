package com.evo.navigation.impl.di

import com.evo.navigation.api.BackstackHandler
import com.evo.navigation.api.EvoNavigationHandler
import com.evo.navigation.api.EvoRoot
import com.evo.navigation.impl.Backstack
import com.evo.navigation.impl.EvoNavigator
import com.evo.navigation.impl.EvoRootImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

object NavigationModule {
    private val module = module {
        singleOf(::Backstack) bind BackstackHandler::class
        singleOf(::EvoNavigator) bind EvoNavigationHandler::class
        singleOf(::EvoRootImpl) bind EvoRoot::class
    }

    operator fun invoke() = module
}