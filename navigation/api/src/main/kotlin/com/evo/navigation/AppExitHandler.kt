package com.evo.navigation

import kotlinx.coroutines.flow.Flow

interface AppExitHandler {

    val exitCommandFlow: Flow<Unit>

    fun exit()

}
