package com.evo.navigation

import kotlinx.coroutines.flow.StateFlow

interface Backstack {

    val lastScreenFlow: StateFlow<EvoContentOwner>

    fun addUnique(tab: EvoTab)

    fun put(screen: EvoContentOwner)

    fun replace(screen: EvoContentOwner)

    fun dropLast()
}
