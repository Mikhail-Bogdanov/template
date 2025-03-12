package com.evo.navigation

interface EvoNavigationHandler {

    fun navigate(screen: Screen, navBuilder: NavPropertiesHandler.() -> Unit = {})

    fun pop(popBuilder: PopPropertiesHandler.() -> Unit = {})

}