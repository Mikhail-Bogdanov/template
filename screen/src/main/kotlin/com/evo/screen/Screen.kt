package com.evo.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf
import kotlin.reflect.KClass

@Immutable
abstract class Screen<ARGS : Any, SM : BaseScreenModel<*>>(
    args: ARGS,
    smClass: KClass<SM>,
) : KoinComponent {

    protected val sm: SM = this.getKoin().get(smClass) { parametersOf(args) }
    protected val state = sm.state

    context(EvoNavigator, EvoEventHandler)
    @Composable
    abstract fun Content()

}

data object NoArgs

typealias EvoScreen = Screen<*, *>
