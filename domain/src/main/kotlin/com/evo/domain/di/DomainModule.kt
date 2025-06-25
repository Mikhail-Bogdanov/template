package com.evo.domain.di

import org.koin.dsl.module

object DomainModule {
    val module = module {
    }

    operator fun invoke() = module
}
