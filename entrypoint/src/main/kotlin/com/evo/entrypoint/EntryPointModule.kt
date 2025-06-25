package com.evo.entrypoint

import com.evo.screen.EvoEventHandler
import com.evo.screen.EvoNavigator
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.binds
import org.koin.dsl.module

object EntryPointModule {

    private val module = module {
        viewModelOf(::EntryPointViewModel) binds arrayOf(
            EvoNavigator::class,
            EvoEventHandler::class,
        )

        single {
            androidContext().resources.configuration
        }
    }

    operator fun invoke() = module
}
