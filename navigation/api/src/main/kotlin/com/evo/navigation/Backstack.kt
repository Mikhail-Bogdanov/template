package com.evo.navigation

import kotlinx.coroutines.flow.StateFlow

interface Backstack {

    val lastScreenFlow: StateFlow<EvoScreen>

    fun put(screen: EvoScreen)

    fun replace(screen: EvoScreen)

    fun dropLast()
}
