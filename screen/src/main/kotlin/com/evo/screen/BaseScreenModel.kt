package com.evo.screen

import androidx.compose.runtime.Immutable
import com.evo.domain.extensions.SuspendLambda
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@Immutable
abstract class BaseScreenModel<STATE> {

    abstract val state: STATE

    protected fun updateState(action: STATE.() -> Unit) = state.apply(action)

    val scope: CoroutineScope = CoroutineScope(Dispatchers.Main.immediate)

    protected fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        action: SuspendLambda,
    ) = scope.launch(context = context, block = action)
}
