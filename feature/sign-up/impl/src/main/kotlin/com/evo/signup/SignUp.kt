package com.evo.signup

import androidx.compose.runtime.Composable
import com.evo.screen.Screen
import com.evo.screen.Screens
import org.koin.core.component.inject

internal class SignUp : Screen<ScreenModel>(Screens.SignUp) {

    override val screenModel: ScreenModel by inject()

    @Composable
    override fun Content() {

    }
}
