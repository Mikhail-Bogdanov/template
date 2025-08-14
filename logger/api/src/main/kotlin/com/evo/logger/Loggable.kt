package com.evo.logger

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class Loggable : KoinComponent {

    val evoLogger: EvoLogger by inject()

    fun <Child : Loggable> Child.loge(message: Any?) {
        with(evoLogger) { loge(message) }
    }

    fun <Child : Loggable> Child.logi(message: Any?) {
        with(evoLogger) { logi(message) }
    }
}
