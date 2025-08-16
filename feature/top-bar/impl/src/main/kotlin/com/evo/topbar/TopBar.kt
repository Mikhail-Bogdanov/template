package com.evo.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.evo.navigation.EvoNavigator
import com.evo.presentation.ui.IconResource
import com.evo.presentation.ui.designsystem.atoms.*
import com.evo.presentation.ui.designsystem.theme.ClickInfo
import com.evo.presentation.ui.designsystem.theme.DesignSystem
import com.evo.presentation.ui.designsystem.theme.DesignSystem.Shapes.ExtraLarge.asTopBar
import org.koin.core.component.inject

internal class TopBarImpl : TopBar() {

    private val navigator: EvoNavigator by inject()

    @Composable
    override fun Content(args: TopBarArgs) {
        val navIcon = if (args.defaultBackButton) {
            DSIcon(
                resource = IconResource.ChevronLeft,
                colors = DesignSystem.primaryIconColors(),
                clickInfo = ClickInfo(onClick = navigator::pop),
            )
        } else {
            args.navigationIcon
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = args.containerColor ?: DesignSystem.Colors.container.primary,
                    shape = DesignSystem.Shapes.Big.asTopBar(),
                )
                .statusBarsPadding()
                .padding(DesignSystem.Paddings.DSPx1),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            navIcon?.let { DesignSystem.Icon(icon = it) }
            args.title?.let {
                DesignSystem.Text(
                    modifier = Modifier.weight(1f),
                    text = it,
                    style = DesignSystem.TextStyles.title,
                    color = args.titleColor ?: DesignSystem.Colors.content.primary,
                )
            }
            if (args.actions.isNotEmpty()) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        DesignSystem.Paddings.DSPx2,
                        Alignment.End,
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    args.actions.forEach { DesignSystem.Icon(icon = it) }
                }
            }
        }
    }
}
