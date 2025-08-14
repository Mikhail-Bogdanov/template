package com.evo.crash

import com.evo.navigation.BaseScreen
import com.evo.navigation.BaseScreenModel

abstract class Crash<SM : BaseScreenModel<*>> : BaseScreen<SM, CrashArgs>()

data class CrashArgs(
    val sampleData: Int,
)
