package com.evo.theme

import androidx.compose.runtime.Composable
import com.evo.presentation.ui.designsystem.atoms.*
import com.evo.presentation.ui.designsystem.theme.*
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

internal class ThemeImpl(
      args: ThemeArgs,
) : Theme<ScreenModel>() {

    override val screenModel: ScreenModel by inject {
        parametersOf(args)
    }

    @Composable
    override fun Content() {
        DesignSystem.ScreenScaffold(
            
        ) {
            
        }
    }
}