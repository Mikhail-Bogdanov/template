package com.evo.presentation.ui

import androidx.compose.runtime.Composable
import com.evo.logger.EvoLogger
import com.evo.logger.Loggable
import org.koin.compose.koinInject
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class SharedComposableArgs<ARGS : Any> : Loggable, KoinComponent {

    override val evoLogger: EvoLogger by inject()

    @Composable
    abstract fun Content(args: ARGS)

}

abstract class SharedComposable : Loggable, KoinComponent {

    override val evoLogger: EvoLogger by inject()

    @Composable
    abstract fun Content()

}

@Composable
inline fun <reified SC : SharedComposable> share() {
    koinInject<SC>().Content()
}

@Composable
inline fun <reified SC : SharedComposableArgs<ARGS>, ARGS : Any> share(args: ARGS) {
    koinInject<SC>().Content(args)
}
