package com.evo.permission

import androidx.compose.runtime.Composable
import com.evo.presentation.ui.designsystem.atoms.*
import com.evo.presentation.ui.designsystem.theme.*
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

internal class PermissionImpl : Permission<ScreenModel>() {

    override val args: PermissionArgs by inject()

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
