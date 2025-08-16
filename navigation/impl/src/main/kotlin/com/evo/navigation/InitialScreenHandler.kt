package com.evo.navigation

import com.evo.coroutine.ScopeProvider
import com.evo.firstscreen.FirstScreen
import com.evo.navigation.InitialScreenHandler.InitialScreen
import com.evo.storage.EvoStorage
import com.evo.storage.EvoStorageSpec
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class InitialScreenHandlerImpl(
    private val evoStorage: EvoStorage,
    scopeProvider: ScopeProvider,
    private val firstScreen: FirstScreen<*>,
) : InitialScreenHandler {

    private val scope = scopeProvider.provideIO()

    private val key = EvoStorageSpec.StringSpec("initialScreen", InitialScreen.Login.name)

    override fun get() = evoStorage.getSync(key).asInitialScreen().let {
        when (it) {
            InitialScreen.Login -> firstScreen
            InitialScreen.Update -> TODO()
            InitialScreen.Start -> TODO()
        }
    }

    override fun set(screen: InitialScreen) {
        scope.launch(Dispatchers.IO) {
            evoStorage.set(key, screen.name)
        }
    }

}

private fun String.asInitialScreen() = InitialScreen.entries.firstOrNull {
    it.name == this
} ?: InitialScreen.Login
