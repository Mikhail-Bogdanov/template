package com.evo.locale

import com.evo.navigation.BaseScreen
import com.evo.navigation.BaseScreenModel

abstract class Locale <SM : BaseScreenModel<*>> : BaseScreen<SM>()

data class LocaleArgs(
    val sampleData: Int,
)