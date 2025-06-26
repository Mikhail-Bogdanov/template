package com.evo.screen

interface EvoNavigator {

    fun <ARGS : Any> navigate(screen: Screens, args: ARGS)

    fun navigate(screen: Screens)

    fun <ARGS : Any> replace(screen: Screens, args: ARGS)

    fun replace(screen: Screens)

    fun pop()

}
