package com.evo.settings

import com.evo.screen.Screens
import org.koin.core.qualifier.named
import org.koin.dsl.module

object SettingsModule {

    val module = module {

        factory(named(Screens.Settings)) {
            Settings()
        }

    }
}
