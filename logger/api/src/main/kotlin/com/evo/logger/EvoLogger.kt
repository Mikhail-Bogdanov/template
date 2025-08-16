package com.evo.logger

interface EvoLogger {

    /**
     * Log Exception
     */
    fun <Child : Loggable> Child.loge(message: Any?)

    /**
     * Log Info
     */
    fun <Child : Loggable> Child.logi(message: Any?)

}
