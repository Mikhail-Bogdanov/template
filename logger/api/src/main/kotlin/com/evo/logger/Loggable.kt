package com.evo.logger

interface Loggable {

    val evoLogger: EvoLogger
}

fun <Child : Loggable> Child.loge(message: Any?) {
    with(evoLogger) { loge(message) }
}

fun <Child : Loggable> Child.logi(message: Any?) {
    with(evoLogger) { logi(message) }
}
