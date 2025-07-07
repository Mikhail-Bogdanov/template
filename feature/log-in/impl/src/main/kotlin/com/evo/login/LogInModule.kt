package com.evo.login

import com.evo.screen.Screens
import org.koin.core.qualifier.named
import org.koin.dsl.module

object LogInModule {

    val module = module {
        
		factory(named(Screens.LogIn)) {
    		LogIn()
		}

    }
}
