package com.evo.coroutine

import kotlinx.coroutines.CoroutineScope

interface ScopeProvider {

    fun provideIO(): CoroutineScope

    fun cancel()

}
