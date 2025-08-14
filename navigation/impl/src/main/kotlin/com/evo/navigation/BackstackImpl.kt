package com.evo.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.LinkedList

internal class BackstackImpl(
    initialScreenHandler: InitialScreenHandler,
) : Backstack {

    private val initial: EvoScreen = initialScreenHandler.get().let {
        when (it) {
            InitialScreen.Login -> TODO()
            InitialScreen.Update -> TODO()
            InitialScreen.Start -> TODO()
        }
    }

    private val _lastScreenFlow = MutableStateFlow(initial)
    override val lastScreenFlow = _lastScreenFlow.asStateFlow()

    private val screens = LinkedList(mutableListOf(initial))

    override fun put(screen: EvoScreen) {
        screens.addLast(screen)
        _lastScreenFlow.value = screen
    }

    override fun replace(screen: EvoScreen) {
        screens.removeLast()
        screens.addLast(screen)
        _lastScreenFlow.value = screen
    }

    override fun dropLast() {
        screens.removeLast()
        _lastScreenFlow.value = screens.last
    }

}
