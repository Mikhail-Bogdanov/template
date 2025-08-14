package com.evo.internal

import android.util.Log

abstract class SafeWrapper {

    protected inline fun <Child : SafeWrapper, T> Child.wrapResultNullable(block: () -> T): T? {
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

    protected fun <Child : Any> Child.loge(message: Any?) {
        val childClass = this::class
        val className = childClass.simpleName ?: "Anonymous class"
        Log.d("[EXCEPTION] Class: $className", "Message: $message")
    }

    protected fun <Child : Any> Child.logi(message: Any?) {
        val childClass = this::class
        val className = childClass.simpleName ?: "Anonymous class"
        Log.d("[INFO] Class: $className", "Message: $message")
    }
}
