package com.evo.navigation.api

import com.evo.navigation.Screen

interface EvoNavigationHandler {

    fun navigate(screen: Screen, navBuilder: NavPropertiesHandler.() -> Unit = {})

    fun pop(popBuilder: PopPropertiesHandler.() -> Unit = {})

}