package com.evo.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Immutable
abstract class Screen<SM : BaseScreenModel<*>> : KoinComponent {

    protected abstract val screenModel: SM

    private val eventHandler: EvoEventHandler by inject()
    protected val navigator: EvoNavigator by inject()

    @Composable
    abstract fun Content()

    protected fun send(event: EvoEvent) = eventHandler.event(event)

}

data object NoArgs

typealias EvoScreen = Screen<*>
