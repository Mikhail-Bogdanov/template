package com.evo.crash

import com.evo.navigation.*

abstract class Crash<SM : EvoScreenModel> : BaseScreen<SM>(), ArgsOwner<CrashArgs>

data class CrashArgs(
    val sampleData: Int,
)
