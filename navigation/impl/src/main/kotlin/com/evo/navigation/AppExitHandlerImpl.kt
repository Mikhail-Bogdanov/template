package com.evo.navigation

import kotlinx.coroutines.flow.MutableSharedFlow

class AppExitHandlerImpl : AppExitHandler {

    override val exitCommandFlow = MutableSharedFlow<Unit>(1, 1)

    override fun exit() {
        exitCommandFlow.tryEmit(Unit)
    }
}
