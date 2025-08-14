package com.evo.navigation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class ActiveScreenFlowHandlerImpl(
    private val backstack: Backstack,
) : ActiveScreenFlowHandler {

    override fun observerIsScreenActive(screen: EvoScreen): Flow<Boolean> {
        return backstack.lastScreenFlow.map { it === screen }
    }
}
