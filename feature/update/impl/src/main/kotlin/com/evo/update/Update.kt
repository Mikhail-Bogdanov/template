package com.evo.update

import androidx.compose.runtime.Composable
import com.evo.presentation.ui.designsystem.atoms.ScreenContent
import com.evo.presentation.ui.designsystem.theme.DesignSystem
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

internal class UpdateImpl : Update<ScreenModel>() {

    override val screenModel: ScreenModel by inject {
        parametersOf()
    }

    @Composable
    override fun Content() {
        DesignSystem.ScreenContent {

        }
    }
}
