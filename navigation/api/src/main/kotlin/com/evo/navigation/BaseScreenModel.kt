package com.evo.navigation

import androidx.compose.runtime.Immutable
import com.evo.domain.extensions.SuspendLambda
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import kotlin.coroutines.CoroutineContext

@Immutable
abstract class BaseScreenModel<STATE> : KoinComponent {
    abstract val state: STATE

    protected fun updateState(action: STATE.() -> Unit) = state.apply(action)

    val scope: CoroutineScope = CoroutineScope(Dispatchers.Main.immediate)

    internal var isScreenActive = true

    protected fun launch(
        context: CoroutineContext = Dispatchers.IO,
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
        scope.cancel("BaseScreen model is cleared")
    }
}
