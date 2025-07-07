package com.evo.entrypoint

import androidx.compose.ui.util.fastForEachIndexed
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evo.screen.*
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

internal class EntryPointViewModel(
    initialScreen: EvoScreen,
) : ViewModel(), EvoNavigator, EvoEventHandler, KoinComponent {

    private val backstackFlow = flow {
        emit(persistentListOf(initialScreen))
    }.onEach { screens ->
        screens.fastForEachIndexed { index, screen ->
            val isActive = index == screens.lastIndex - 1 || index == screens.lastIndex
            screen.emitIsActive(isActive)
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, persistentListOf(initialScreen))

    internal val lastScreenFlow = backstackFlow.map { it.lastOrNull() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, initialScreen)
    private val last get() = lastScreenFlow.value

    val events = Channel<EvoEvent>(EVO_EVENTS_CHANNEL_CAPACITY, BufferOverflow.DROP_OLDEST)

    override fun event(event: EvoEvent) {
        events.trySend(event)
    }

    override fun <ARGS : Any> navigate(screen: Screens, args: ARGS) {
        val screenImpl = retrieveScreen(screen, args)

        screenImpl.onEnter()
        pushScreen(screenImpl)
    }

    override fun navigate(screen: Screens) {
        val screenImpl = retrieveScreen(screen)

        screenImpl.onEnter()
        pushScreen(screenImpl)
    }

    override fun <ARGS : Any> replace(screen: Screens, args: ARGS) {
        last?.onExit()
        val screenImpl = retrieveScreen(screen, args)
        screenImpl.onEnter()

        popScreen()
        pushScreen(screenImpl)
    }

    override fun replace(screen: Screens) {
        last?.onExit()
        val screenImpl = retrieveScreen(screen)
        screenImpl.onEnter()

        popScreen()
        pushScreen(screenImpl)
    }

    override fun pop() {
        if (backstackFlow.value.size > 1) {
            last?.onExit()
            popScreen()
            last?.onReturn()
        } else {
            event(EvoEvent.Exit)
        }
    }

    private fun <ARGS : Any> retrieveScreen(screen: Screens, args: ARGS): EvoScreen {
        return backstackFlow.value.find {
            it.navigationMark == screen && it.navigationMark.isSingleton
        }?.also {
            backstackFlow.value.remove(it)
        } ?: inject<EvoScreen>(qualifier = named(screen)) {
            parametersOf(args)
        }.value
    }

    private fun retrieveScreen(screen: Screens): EvoScreen {
        return backstackFlow.value.find {
            it.navigationMark == screen && it.navigationMark.isSingleton
        }?.also {
            backstackFlow.value.remove(it)
        } ?: inject<EvoScreen>(qualifier = named(screen)).value
    }

    private fun pushScreen(screen: EvoScreen) {
        backstackFlow.update { it.add(screen) }
    }

    private fun popScreen() {
        backstackFlow.update { it.removeAt(it.lastIndex) }
    }

    companion object {

        private const val EVO_EVENTS_CHANNEL_CAPACITY = 5
    }
}
