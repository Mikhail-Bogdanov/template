package com.evo.entrypoint

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.evo.screen.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

internal class EntryPointViewModel(
    initialScreen: EvoScreen,
) : ViewModel(), EvoNavigator, EvoEventHandler, KoinComponent {

    private val backstack = mutableStateListOf(initialScreen)
    val current get() = backstack.last()

    val events = Channel<EvoEvent>(EVO_EVENTS_CHANNEL_CAPACITY, BufferOverflow.DROP_OLDEST)

    override fun event(event: EvoEvent) {
        events.trySend(event)
    }

    override fun <ARGS : Any> navigate(screen: Screens, args: ARGS) {
        val screenImpl by inject<EvoScreen>(qualifier = named(screen)) {
            parametersOf(args)
        }
        backstack.add(screenImpl)
    }

    override fun navigate(screen: Screens) {
        val screenImpl by inject<EvoScreen>(qualifier = named(screen))
        backstack.add(screenImpl)
    }

    override fun <ARGS : Any> replace(screen: Screens, args: ARGS) {
        val screenImpl by inject<EvoScreen>(qualifier = named(screen)) {
            parametersOf(args)
        }
        backstack.dropLast(1)
        backstack.add(screenImpl)
    }

    override fun replace(screen: Screens) {
        val screenImpl by inject<EvoScreen>(qualifier = named(screen))
        backstack.dropLast(1)
        backstack.add(screenImpl)
    }

    override fun pop() {
        if (backstack.size > 1) {
            backstack.dropLast(1)
        } else {
            event(EvoEvent.Exit)
        }
    }

    companion object {

        private const val EVO_EVENTS_CHANNEL_CAPACITY = 5
    }
}
