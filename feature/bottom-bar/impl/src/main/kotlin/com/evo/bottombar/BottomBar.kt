package com.evo.bottombar

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.evo.logger.logi
import com.evo.navigation.BaseTab
import com.evo.navigation.EvoNavigator
import com.evo.presentation.ui.conditional
import com.evo.presentation.ui.designsystem.atoms.*
import com.evo.presentation.ui.designsystem.theme.*
import com.evo.presentation.ui.designsystem.theme.DesignSystem.Shapes.ExtraLarge.asBottomBar
import kotlinx.collections.immutable.persistentMapOf
import org.koin.core.component.inject

internal class BottomBarImpl : BottomBar() {

    private val navigator: EvoNavigator by inject()

//    private val firstScreen: StartScreen<*> by inject()

    @Composable
    override fun Content(selectedTab: BaseTab<*>) {
//        val screenIcon = firstScreen.retrieveDSIcon()

        val tabs = remember {
            persistentMapOf<BaseTab<*>, DSIcon>(
//                firstScreen to screenIcon,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(DesignSystem.Shapes.None.asBottomBar(IS_FLOATING))
                .conditional(IS_FLOATING) {
                    padding(DesignSystem.Paddings.DSPx4)
                }
                .background(DesignSystem.Colors.container.primary)
                .navigationBarsPadding()
                .padding(DesignSystem.Paddings.DSPx1),
            horizontalArrangement = Arrangement.spacedBy(DesignSystem.Paddings.DSPx2),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            tabs.keys.forEach { key ->
                val icon = tabs.getValue(key)
                logi("Selected tab: $selectedTab")
                val isSelected = selectedTab == key
                val finalIcon = if (isSelected) {
                    icon.copy(colors = DesignSystem.highlightIconColors())
                } else {
                    icon.copy(colors = DesignSystem.primaryIconColors())
                }
                DesignSystem.Icon(
                    modifier = Modifier.weight(1f),
                    icon = finalIcon,
                )
            }
        }
    }

    @Composable
    private fun BaseTab<*>.retrieveDSIcon() = DSIcon(
        resource = tabIcon.resource,
        text = tabIcon.text,
        colors = DesignSystem.primaryIconColors(),
        clickInfo = ClickInfo {
            navigator.setTab(this)
        },
    )

    companion object {

        private const val IS_FLOATING = false
    }
}
