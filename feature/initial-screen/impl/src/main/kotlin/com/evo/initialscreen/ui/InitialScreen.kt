package com.evo.initialscreen.ui

import androidx.compose.runtime.Composable
import com.evo.screen.*

internal class InitialScreen : Screen<NoArgs, ScreenModel>(NoArgs, ScreenModel::class) {

    context(EvoNavigator, EvoEventHandler)
    @Composable
    override fun Content() {

    }
}
