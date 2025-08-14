package com.evo.coroutine

import kotlinx.coroutines.CoroutineScope

interface ScopeProvider {

    fun provide(): CoroutineScope

}
