package com.evo.entrypoint

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.evo.screen.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import org.koin.core.component.KoinComponent

internal class EntryPointViewModel(
    initialScreen: EvoScreen,
) : ViewModel(), EvoNavigator, EvoEventHandler, KoinComponent {

    private val backstack = mutableStateListOf(initialScreen)
    val current get() = backstack.last()

    val events = Channel<EvoEvent>(EVO_EVENTS_CHANNEL_CAPACITY, BufferOverflow.DROP_OLDEST)

    override fun event(event: EvoEvent) {
        events.trySend(event)
    }

    override fun navigate(screen: EvoScreen) {
        backstack.add(screen)
    }

    override fun replace(screen: EvoScreen) {
        backstack.dropLast(1)
        backstack.add(screen)
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
