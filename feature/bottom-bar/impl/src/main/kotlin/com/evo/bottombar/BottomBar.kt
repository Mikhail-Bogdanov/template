package com.evo.bottombar

import androidx.compose.runtime.Composable
import com.evo.presentation.ui.designsystem.atoms.ScreenScaffold
import com.evo.presentation.ui.designsystem.theme.DesignSystem
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

internal class BottomBarImpl(

) : BottomBar<ScreenModel>() {

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
