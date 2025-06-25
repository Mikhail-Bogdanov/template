package com.evo.screen

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

interface EvoNavigator {

    fun navigate(screen: EvoScreen)

    fun replace(screen: EvoScreen)

    fun pop()

}

context(KoinComponent)
inline fun <reified SCREEN : EvoScreen> EvoNavigator.navigate() {
    val screen by inject<SCREEN>()
    navigate(screen)
}

context(KoinComponent)
inline fun <reified SCREEN : Screen<ARGS, *>, reified ARGS> EvoNavigator.navigate(params: ARGS) {
    val screen by inject<SCREEN> {
        parametersOf(params)
    }
    navigate(screen)
}

context(KoinComponent)
inline fun <reified SCREEN : Screen<ARGS, *>, reified ARGS> EvoNavigator.replace(params: ARGS) {
    val screen by inject<SCREEN> {
        parametersOf(params)
    }
    replace(screen)
}
