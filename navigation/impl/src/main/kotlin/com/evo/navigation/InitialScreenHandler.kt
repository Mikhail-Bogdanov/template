package com.evo.navigation

import com.evo.login.Login
import com.evo.navigation.InitialScreenHandler.InitialScreen
import com.evo.startscreen.StartScreen
import com.evo.storage.EvoStorage
import com.evo.storage.EvoStorageSpec
import com.evo.update.Update
import com.evo.whatsnew.WhatsNew
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class InitialScreenHandlerImpl(
    private val evoStorage: EvoStorage,
) : InitialScreenHandler, KoinComponent {

    private val key = EvoStorageSpec.StringSpec("initialScreen", InitialScreen.Login.name)

    override suspend fun retrieve(): BaseScreen {
        val initialScreen = evoStorage.getAsync(key).asInitialScreen()

        val screenClass by when (initialScreen) {
            InitialScreen.Login -> inject<Login>()
            InitialScreen.Update -> inject<Update>()
            InitialScreen.Start -> inject<StartScreen>()
            InitialScreen.WhatsNew -> inject<WhatsNew>()
        }
        return screenClass
    }

    override suspend fun set(screen: InitialScreen) {
        evoStorage.set(key, screen.name)
    }
}

private fun String.asInitialScreen() = InitialScreen.entries.firstOrNull {
    it.name == this
} ?: InitialScreen.Login
