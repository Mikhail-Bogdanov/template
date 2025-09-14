package com.evo.crash

import com.evo.navigation.ArgsOwner
import com.evo.navigation.BaseScreen

abstract class Crash : BaseScreen(), ArgsOwner<CrashArgs>

data class CrashArgs(
    val sampleData: Int,
)
