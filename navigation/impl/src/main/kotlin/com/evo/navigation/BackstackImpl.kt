package com.evo.navigation

import com.evo.coroutine.ScopeProvider
import com.evo.logger.*
import kotlinx.coroutines.flow.*
import java.util.LinkedList

internal class BackstackImpl(
    override val evoLogger: EvoLogger,
    private val appExitHandler: AppExitHandler,
    initial: BaseScreen,
    scopeProvider: ScopeProvider,
) : Backstack, Loggable {

    private val _lastScreenFlow = MutableStateFlow(initial)
    override val lastScreenFlow = _lastScreenFlow.onEach {
        screens.forEachIndexed { index, screen ->
            screen.screenModel.isActive = screens.lastIndex == index
        }
    }.stateIn(scopeProvider.provideIO(), SharingStarted.Eagerly, initial)

    private val screens = LinkedList(mutableListOf(initial))

    override fun addUnique(tab: BaseTab) {
        if (screens.lastOrNull() == tab) {
            tab.onReenter()
            logi("Reentered $tab Tab")
        } else {
            tab.onReturn()

            screens.remove(tab) // won't do anything if not contains
            screens.addLast(tab)

            _lastScreenFlow.value = tab

            logi("screens quantity: ${screens.size}")
        }
    }

    override fun put(screen: BaseScreen) {
        screen.onEnter()
        screens.addLast(screen)

        _lastScreenFlow.value = screen

        logi("screens quantity: ${screens.size}")
    }

    override fun replace(screen: BaseScreen) {
        val dropped = screens.removeLast()
        dropped.screenModel.clear()
        dropped.onExit()
        screen.onEnter()

        screens.addLast(screen)

        _lastScreenFlow.value = screen

        logi("screens quantity: ${screens.size}")
    }

    override fun dropLast() {
        if (screens.size == 1) {
            appExitHandler.exit()
        } else {
            val dropped = screens.removeLast()
            dropped.screenModel.clear()
            dropped.onExit()

            val last = screens.last()
            last.onReturn()

            _lastScreenFlow.value = last

            logi("screens quantity: ${screens.size}")
        }
    }
}
