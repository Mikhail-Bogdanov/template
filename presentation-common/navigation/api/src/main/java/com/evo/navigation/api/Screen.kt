package com.evo.navigation.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

@Immutable
abstract class Screen {

    @Composable
    abstract fun Content(navigator: EvoNavigationHandler)
}

interface EvoRoot {
    @Composable
    fun Content(initialScreen: Screen)
}