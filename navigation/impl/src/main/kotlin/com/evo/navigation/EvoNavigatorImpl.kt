package com.evo.navigation

internal class EvoNavigatorImpl(
    private val backstack: Backstack,
) : EvoNavigator {

    override fun navigate(screen: EvoScreen) {
        backstack.put(screen)
    }

    override fun replace(screen: EvoScreen) {
        backstack.replace(screen)
    }

    override fun pop() {
        backstack.dropLast()
    }
}
