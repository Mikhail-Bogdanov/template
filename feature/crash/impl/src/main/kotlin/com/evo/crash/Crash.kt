package com.evo.crash

import androidx.compose.runtime.Composable
import com.evo.presentation.ui.designsystem.atoms.ScreenContent
import com.evo.presentation.ui.designsystem.theme.DesignSystem
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

internal class CrashImpl : Crash<ScreenModel>() {

    override val args: CrashArgs by inject()

    override val screenModel: ScreenModel by inject {
        parametersOf(args)
    }

    @Composable
    override fun Content() {
        DesignSystem.ScreenContent(

        ) {

        }
    }
}
