package com.evo.settings

import com.evo.navigation.BaseScreen
import com.evo.navigation.BaseScreenModel

abstract class Settings <SM : BaseScreenModel<*>> : BaseScreen<SM>()

data class SettingsArgs(
    val sampleData: Int,
)