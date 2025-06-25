package com.evo.initialscreen

import com.evo.initialscreen.ui.InitialScreen
import com.evo.screen.Screens
import org.koin.core.qualifier.named
import org.koin.dsl.module

object InitialScreenModule {

    val module = module {
        factory(named(Screens.MAIN_PAGE)) {
            InitialScreen()
        }
    }
}
