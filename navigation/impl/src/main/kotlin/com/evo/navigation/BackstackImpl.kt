package com.evo.navigation

import com.evo.logger.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.LinkedList

internal class BackstackImpl(
    override val evoLogger: EvoLogger,
    initial: EvoContentOwner,
    private val appExitHandler: AppExitHandler,
) : Backstack, Loggable {

    private val _lastScreenFlow = MutableStateFlow(initial)
    override val lastScreenFlow = _lastScreenFlow.asStateFlow()

    private val screens = LinkedList(mutableListOf(initial))

    override fun addUnique(tab: EvoTab) {
        if (screens.last() === tab) {
            tab.onReturn()
            return
        }

        _lastScreenFlow.value = tab

        screens.remove(tab) // won't do anything if not contains
        screens.addLast(tab)
        tab.onReturn()

        logi("screens quantity: ${screens.size}")
    }

    override fun put(screen: EvoContentOwner) {
        _lastScreenFlow.value = screen
        screens.addLast(screen)
        screen.onEnter()

        logi("screens quantity: ${screens.size}")
    }

    override fun replace(screen: EvoContentOwner) {
        _lastScreenFlow.value = screen
        val dropped = screens.removeLast()
        dropped.onExit()
        screens.addLast(screen)
        screen.onEnter()

        logi("screens quantity: ${screens.size}")
    }

    override fun dropLast() {
        if (screens.size == 1) {
            appExitHandler.exit()
            return
        }
        val dropped = screens.removeLast()
        val last = screens.last()
        _lastScreenFlow.value = last
        dropped.onExit()
        last.onReturn()

        logi("screens quantity: ${screens.size}")
    }
}
