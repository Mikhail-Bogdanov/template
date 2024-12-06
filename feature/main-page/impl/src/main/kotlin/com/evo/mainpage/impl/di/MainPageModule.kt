package com.evo.mainpage.di

import com.evo.mainpage.api.ui.MainPage
import com.evo.mainpage.impl.ui.MainPageImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

object MainPageModule {
    private val module = module {
        factoryOf(::MainPageImpl) bind MainPage::class
    }

    operator fun invoke() = module
}