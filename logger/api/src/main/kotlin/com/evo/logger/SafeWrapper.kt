package com.evo.logger

abstract class SafeWrapper : Loggable() {

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
