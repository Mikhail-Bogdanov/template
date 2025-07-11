package com.evo.signup

import com.evo.di.EvoModule
import com.evo.screen.Screens
import org.koin.core.module.Module
import org.koin.core.qualifier.named

class SignUpModule : EvoModule {

	override fun Module.initialize() {
		factory(named(Screens.SignUp)) {
			SignUp()
		}
	}
}
