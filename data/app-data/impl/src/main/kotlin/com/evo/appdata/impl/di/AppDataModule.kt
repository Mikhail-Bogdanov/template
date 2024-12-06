package com.evo.appdata.impl.di

import com.evo.appdata.api.locale.LocaleHandler
import com.evo.appdata.api.theme.ThemeHandler
import com.evo.appdata.impl.locale.LocaleHandlerImpl
import com.evo.appdata.impl.theme.ThemeHandlerImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

object AppDataModule {
    private val module = module {
        factoryOf(::ThemeHandlerImpl) bind ThemeHandler::class
        factoryOf(::LocaleHandlerImpl) bind LocaleHandler::class
    }

    operator fun invoke() = module
}