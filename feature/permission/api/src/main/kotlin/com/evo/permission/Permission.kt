package com.evo.permission

import com.evo.navigation.BaseScreen
import com.evo.navigation.BaseScreenModel

abstract class Permission<SM : BaseScreenModel<*>> : BaseScreen<SM, PermissionArgs>()


data class PermissionArgs(
    val sampleData: Int,
)