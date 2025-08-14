package com.evo.easteregg

import com.evo.navigation.BaseScreen
import com.evo.navigation.BaseScreenModel

abstract class EasterEgg<SM : BaseScreenModel<*>> : BaseScreen<SM, EasterEggArgs>()

data class EasterEggArgs(
    val sampleData: Int,
)
