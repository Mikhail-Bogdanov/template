package com.evo.navigation

import kotlinx.coroutines.flow.StateFlow

interface Backstack {

    val lastScreenFlow: StateFlow<BaseScreen>

    fun addUnique(tab: BaseTab)

    fun put(screen: BaseScreen)

    fun replace(screen: BaseScreen)

    fun dropLast()
}
