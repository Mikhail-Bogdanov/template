package com.evo.screen

import androidx.compose.runtime.Immutable
import com.evo.domain.extensions.SuspendLambda
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@Immutable
abstract class BaseScreenModel<STATE> : ScreenLifecycleOwner {

    abstract val state: STATE

    protected fun updateState(action: STATE.() -> Unit) = state.apply(action)

    // активен сразу при создании, потому что в большинстве кейсов экран создается и сразу показывается
    internal var isScreenActive = true

    val scope: CoroutineScope = CoroutineScope(Dispatchers.Main.immediate)

    protected fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        action: SuspendLambda,
    ) = scope.launch(context = context, block = action)

    protected fun every(delay: Long = 5_000, block: SuspendLambda) {
        launch {
            while (isActive) {
                if (isScreenActive) {
                    block()
                    delay(delay)
                } else {
                    delay(100)
                }
            }
        }
    }

    internal fun clear() {
        scope.cancel("Screen model is cleared")
    }
}
