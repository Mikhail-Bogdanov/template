package com.evo.navigation

import kotlinx.coroutines.flow.Flow

interface ActiveScreenFlowHandler {

    fun observerIsScreenActive(screen: EvoScreen): Flow<Boolean>

}
