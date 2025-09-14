package com.evo.permission

import com.evo.navigation.*

abstract class Permission : BaseScreen(), ArgsOwner<PermissionArgs>

data class PermissionArgs(
    val sampleData: Int,
)
