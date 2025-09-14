package com.evo.navigation

import androidx.compose.runtime.Immutable
import com.evo.domain.extensions.CoroutineLambda
import com.evo.domain.extensions.SuspendLambda
import com.evo.logger.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.flow.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.Timer
import kotlin.concurrent.scheduleAtFixedRate
import kotlin.coroutines.CoroutineContext

@Immutable
abstract class BaseScreenModel<STATE> : Loggable, KoinComponent, EvoLifecycleOwner {

    abstract val state: STATE
    protected fun state(mutate: STATE.() -> Unit) = state.apply(mutate)

    final override val evoLogger: EvoLogger by inject()

    val scope: CoroutineScope = CoroutineScope(Dispatchers.Main.immediate)
    private val timer = Timer("ScreenModel timer", false)

    private val isActiveFlow = MutableSharedFlow<Boolean>(1, 1, BufferOverflow.DROP_OLDEST)
    var isActive: Boolean = true
        set(value) {
            isActiveFlow.tryEmit(value)
        }

    protected fun launch(
        context: CoroutineContext = Dispatchers.IO,
        action: CoroutineLambda,
    ) = scope.launch(context = context, block = action)

    protected fun every(period: Long, block: SuspendLambda) {
        val periodicWorkChannel = Channel<Unit>(UNLIMITED)

        timer.scheduleAtFixedRate(
            delay = 0,
            period = period,
        ) {
            periodicWorkChannel.trySend(Unit)
        }

        combine(periodicWorkChannel.receiveAsFlow(), isActiveFlow) { work, isActive ->
            work.takeIf { isActive }
        }.filterNotNull().debounce(period).onEach {
            block()
        }.launchIn(scope)
    }

    fun clear() {
        scope.cancel("ScreenModel is cleared")
        timer.cancel()
        timer.purge()
        logi("is cleared")
    }
}

interface ScreenModelOwner {

    val screenModel: BaseScreenModel<*>
}
