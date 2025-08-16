package com.evo.bottombar

import com.evo.navigation.BaseTab
import com.evo.presentation.ui.SharedComposableArgs

abstract class BottomBar : SharedComposableArgs<BottomBarArgs>()

data class BottomBarArgs(
    val selectedTab: BaseTab<*>,
)
