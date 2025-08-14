package com.evo.navigation

import com.evo.coroutine.ScopeProvider
import com.evo.storage.EvoStorage
import com.evo.storage.EvoStorageSpec
import kotlinx.coroutines.*

internal class InitialScreenHandler(
    private val evoStorage: EvoStorage,
    scopeProvider: ScopeProvider,
) {

    private val scope = scopeProvider.provide()

    private val key = EvoStorageSpec.StringSpec("initialScreen", InitialScreen.Login.name)

    // TODO think about it!
    fun get() = runBlocking { evoStorage.getAsync(key).asInitialScreen() }

    fun set(screen: InitialScreen) {
        scope.launch(Dispatchers.IO) {
            evoStorage.set(key, screen.name)
        }
    }

}

internal enum class InitialScreen {

    Login, Update, Start,

}

private fun String.asInitialScreen() = InitialScreen.entries.firstOrNull {
    it.name == this
} ?: InitialScreen.Login
