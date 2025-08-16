package com.evo.navigation

import androidx.compose.runtime.*
import com.evo.domain.extensions.SuspendLambda
import com.evo.logger.*
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

typealias EvoScreenModel = BaseScreenModel<*>

@Immutable
abstract class BaseScreenModel<STATE> : Loggable, KoinComponent {
    abstract val state: STATE

    final override val evoLogger: EvoLogger by inject()

    protected fun updateState(action: STATE.() -> Unit) = state.apply(action)

    val scope: CoroutineScope = CoroutineScope(Dispatchers.Main.immediate)

    internal var isScreenActive by mutableStateOf(true)

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
        scope.cancel("ScreenModel is cleared")
        logi("is cleared")
    }
}

interface ScreenModelOwner<SM : EvoScreenModel> {

    val screenModel: SM
}
