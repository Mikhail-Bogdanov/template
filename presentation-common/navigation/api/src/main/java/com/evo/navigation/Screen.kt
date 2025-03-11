package com.evo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import org.koin.compose.koinInject

@Immutable
interface Screen {

    @Composable
    fun Content(navigator: EvoNavigationHandler)
}

interface EvoRoot {
    @Composable
    fun Content(initialScreen: Screen)
}

@Composable
fun EvoRoot(initialScreen: Screen) = koinInject<EvoRoot>().Content(initialScreen)