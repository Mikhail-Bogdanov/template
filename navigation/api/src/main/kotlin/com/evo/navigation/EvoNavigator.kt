package com.evo.navigation

import androidx.compose.runtime.staticCompositionLocalOf

interface EvoNavigator {

    fun setTab(tab: EvoTab)

    fun navigate(screen: EvoScreen)

    fun replace(screen: EvoScreen)

    fun pop()

}

val LocalNavigator = staticCompositionLocalOf<EvoNavigator> { error("EvoNavigator is not provided!") }
