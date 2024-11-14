package com.evo.navigation.api

interface EvoNavigationHandler {

    fun navigate(screen: Screen, navBuilder: NavPropertiesHandler.() -> Unit = {})

    fun pop(popBuilder: PopPropertiesHandler.() -> Unit = {})

}