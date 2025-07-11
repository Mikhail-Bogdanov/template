package com.evo.login

import com.evo.di.EvoModule
import com.evo.screen.Screens
import org.koin.core.module.Module
import org.koin.core.qualifier.named

class LogInModule : EvoModule {

	override fun Module.initialize() {
		factory(named(Screens.LogIn)) {
			LogIn()
		}
	}
}
