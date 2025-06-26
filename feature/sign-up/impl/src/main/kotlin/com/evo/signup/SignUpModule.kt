package com.evo.signup

import com.evo.screen.Screens
import org.koin.core.qualifier.named
import org.koin.dsl.module

object SignUpModule {

    val module = module {
        
		factory(named(Screens.SignUp)) {
    		SignUp()
		}

    }
}