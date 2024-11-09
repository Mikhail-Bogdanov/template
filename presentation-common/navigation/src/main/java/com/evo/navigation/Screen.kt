package com.evo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import com.evo.navigation.api.EvoNavigationHandler

@Immutable
abstract class Screen {

    @Composable
    abstract fun Content(navigator: EvoNavigationHandler)
}