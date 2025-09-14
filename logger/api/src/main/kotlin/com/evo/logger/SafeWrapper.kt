package com.evo.logger

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class SafeWrapper : Loggable, KoinComponent {

    override val evoLogger: EvoLogger by inject()

    protected inline fun <Child : SafeWrapper, T> Child.wrap(block: () -> T): Result<T> {
        return runCatching {
            block()
        }.onFailure {
            loge(it.localizedMessage)
        }
    }
}
