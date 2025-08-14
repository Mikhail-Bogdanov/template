package com.evo.theme

import com.evo.navigation.BaseScreen
import com.evo.navigation.BaseScreenModel

abstract class Theme <SM : BaseScreenModel<*>> : BaseScreen<SM>()

data class ThemeArgs(
    val sampleData: Int,
)