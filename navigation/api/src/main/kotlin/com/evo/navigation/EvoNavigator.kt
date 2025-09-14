package com.evo.navigation

import androidx.compose.runtime.staticCompositionLocalOf
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

interface EvoNavigator : KoinComponent {

    fun setTab(tab: BaseTab)

    fun navigate(screen: BaseScreen)

    fun replace(screen: BaseScreen)

    fun pop()

}

inline fun <reified SCREEN : BaseScreen> EvoNavigator.navigate() {
    val screen: SCREEN by inject()
    navigate(screen)
}

inline fun <reified SCREEN, ARGS : Any> EvoNavigator.navigate(
    args: ARGS,
) where SCREEN : BaseScreen, SCREEN : ArgsOwner<ARGS> {
    val screen: SCREEN by inject {
        parametersOf(args)
    }
    navigate(screen)
}

inline fun <reified SCREEN : BaseScreen> EvoNavigator.replace() {
    val screen: SCREEN by inject()
    replace(screen)
}

inline fun <reified SCREEN, ARGS : Any> EvoNavigator.replace(
    args: ARGS,
) where SCREEN : BaseScreen, SCREEN : ArgsOwner<ARGS> {
    val screen: SCREEN by inject {
        parametersOf(args)
    }
    replace(screen)
}

val LocalNavigator = staticCompositionLocalOf<EvoNavigator> { error("EvoNavigator is not provided!") }
