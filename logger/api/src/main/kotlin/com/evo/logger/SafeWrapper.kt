package com.evo.logger

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class SafeWrapper : Loggable, KoinComponent {

    override val evoLogger: EvoLogger by inject()

    protected inline fun <Child : SafeWrapper, T> Child.wrapResult(block: () -> T): T? {
        return try {
            block()
        } catch (e: Exception) {
            loge(e.localizedMessage)
            null
        }
    }

    protected inline fun <Child : SafeWrapper, T> Child.wrapList(block: () -> List<T>): List<T> {
        return try {
            block()
        } catch (e: Exception) {
            loge(e.localizedMessage)
            emptyList()
        }
    }

    protected inline fun <Child : SafeWrapper> Child.wrapAction(block: () -> Any?) {
        try {
            block()
        } catch (e: Exception) {
            loge(e.localizedMessage)
        }
    }

}
