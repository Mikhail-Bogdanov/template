package com.evo.permission

import com.evo.navigation.*

abstract class Permission<SM : EvoScreenModel> : BaseScreen<SM>(), ArgsOwner<PermissionArgs>

data class PermissionArgs(
    val sampleData: Int,
)
