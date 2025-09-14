package com.evo.navigation

internal class EvoNavigatorImpl(
    private val backstack: Backstack,
) : EvoNavigator {

    override fun setTab(tab: BaseTab) {
        backstack.addUnique(tab)
    }

    override fun navigate(screen: BaseScreen) {
        backstack.put(screen)
    }

    override fun replace(screen: BaseScreen) {
        backstack.replace(screen)
    }

    override fun pop() {
        backstack.dropLast()
    }
}
