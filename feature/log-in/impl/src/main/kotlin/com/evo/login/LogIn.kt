package com.evo.login

import androidx.compose.runtime.Composable
import com.evo.screen.Screen
import org.koin.core.component.inject

internal class LogIn : Screen<ScreenModel>() {

    override val screenModel: ScreenModel by inject()

    @Composable
    override fun Content() {

    }
}
