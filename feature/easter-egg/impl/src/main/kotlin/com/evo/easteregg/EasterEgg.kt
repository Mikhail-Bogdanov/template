package com.evo.easteregg

import androidx.compose.runtime.Composable
import com.evo.presentation.ui.designsystem.atoms.*
import com.evo.presentation.ui.designsystem.theme.*
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

internal class EasterEggImpl(
      args: EasterEggArgs,
) : EasterEgg<ScreenModel>() {

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