package com.evo.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import com.evo.di.EvoScopeModuleOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Immutable
abstract class Screen<SM : BaseScreenModel<*>>(
    val navigationMark: Screens,
) : KoinComponent, ScreenLifecycleOwner, EvoScopeModuleOwner() {

    protected abstract val screenModel: SM

    private val eventHandler: EvoEventHandler by inject()
    protected val navigator: EvoNavigator by inject()

    // активен сразу при создании, потому что в большинстве кейсов экран создается и сразу показывается
    private val isActiveFlow: Channel<Boolean> = Channel(1, BufferOverflow.DROP_OLDEST)

    private val screenScope = CoroutineScope(Dispatchers.Main.immediate)

    init {
        isActiveFlow.receiveAsFlow().onEach { isActive ->
            screenModel.isScreenActive = isActive
        }.launchIn(screenScope)
    }

    suspend fun emitIsActive(isActive: Boolean) {
        isActiveFlow.send(isActive)
    }

    @Composable
    abstract fun Content()

    protected fun send(event: EvoEvent) = eventHandler.event(event)

    final override fun onReturn() = screenModel.onReturn()
    final override fun onExit() {
        screenModel.onExit()
        unloadScopeModule()
        screenModel.clear()
    }
    final override fun onEnter() = screenModel.onEnter()

    final override fun onCreate() = screenModel.onCreate()
    final override fun onResume() = screenModel.onResume()
    final override fun onPause() = screenModel.onPause()
    final override fun onDestroy() = screenModel.onDestroy()
}

typealias EvoScreen = Screen<*>
