package com.evo.signup

import androidx.compose.runtime.Composable
import com.evo.presentation.ui.designsystem.atoms.*
import com.evo.presentation.ui.designsystem.theme.*
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

internal class SignUpImpl : SignUp<ScreenModel>() {

    override val screenModel: ScreenModel by inject {
        parametersOf()
    }

    @Composable
    override fun Content() {
        DesignSystem.ScreenScaffold(

        ) {

        }
    }
}
