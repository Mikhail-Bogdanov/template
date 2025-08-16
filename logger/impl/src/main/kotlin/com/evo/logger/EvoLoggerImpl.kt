package com.evo.logger

import android.util.Log
import com.evo.logger.EvoLoggerImpl.Type.ERROR
import com.evo.logger.EvoLoggerImpl.Type.INFO
import com.evo.logger.impl.BuildConfig
import kotlin.reflect.jvm.jvmName

class EvoLoggerImpl : EvoLogger {

    override fun <Child : Loggable> Child.loge(message: Any?) = withClassName(ERROR, message)

    override fun <Child : Loggable> Child.logi(message: Any?) = withClassName(INFO, message)

    private fun <Child : Loggable> Child.withClassName(type: Type, message: Any?) {
        log(type, this::class.jvmName, message)
    }

    private fun log(type: Type, className: String, message: Any?) {
        if (BuildConfig.DEBUG) {
            Log.d("[${type.name}] Class: $className", "Message: $message")
        }
    }

    private enum class Type { ERROR, INFO }
}
