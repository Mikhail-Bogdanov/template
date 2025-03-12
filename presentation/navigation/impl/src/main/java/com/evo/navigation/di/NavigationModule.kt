package com.evo.navigation.di

import com.evo.navigation.backstack.BackstackHandler
import com.evo.navigation.EvoNavigationHandler
import com.evo.navigation.EvoRoot
import com.evo.navigation.backstack.Backstack
import com.evo.navigation.EvoNavigator
import com.evo.navigation.EvoRootImpl
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