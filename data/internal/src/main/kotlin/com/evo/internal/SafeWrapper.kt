package com.evo.internal

import android.util.Log

abstract class SafeWrapper {

    protected inline fun <Child : Any, T> Child.wrapResultNullable(block: () -> T): T? {
        return try {
            block()
        } catch (e: Exception) {
            log(e.localizedMessage)
            null
        }
    }

    protected inline fun <Child : Any, T> Child.wrapList(block: () -> List<T>): List<T> {
        return try {
            block()
        } catch (e: Exception) {
            log(e.localizedMessage)
            emptyList()
        }
    }

    protected fun <Child : Any> Child.log(message: Any?) {
        val childClass = this::class
        val className = childClass.simpleName ?: "Anonymous class"
        Log.d("[EXCEPTION] Class: $className", "Message: $message")
    }
}
