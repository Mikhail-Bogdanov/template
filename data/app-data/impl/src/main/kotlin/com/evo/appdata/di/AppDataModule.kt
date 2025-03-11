package com.evo.appdata.di

import com.evo.appdata.locale.LocaleHandler
import com.evo.appdata.theme.ThemeHandler
import com.evo.appdata.locale.LocaleHandlerImpl
import com.evo.appdata.theme.ThemeHandlerImpl
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