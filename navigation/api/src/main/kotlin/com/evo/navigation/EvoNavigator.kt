package com.evo.navigation

interface EvoNavigator {

    fun navigate(screen: EvoScreen)

    fun replace(screen: EvoScreen)

    fun pop()

}
