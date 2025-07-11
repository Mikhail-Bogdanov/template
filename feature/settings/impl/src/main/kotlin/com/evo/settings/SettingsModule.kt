package com.evo.settings

import com.evo.di.EvoModule
import com.evo.screen.Screens
import org.koin.core.module.Module
import org.koin.core.qualifier.named

class SettingsModule : EvoModule {

    override fun Module.initialize() {
        factory(named(Screens.Settings)) {
            Settings()
        }
    }
}
