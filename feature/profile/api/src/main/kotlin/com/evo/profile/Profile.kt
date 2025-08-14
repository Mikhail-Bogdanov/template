package com.evo.profile

import com.evo.navigation.BaseScreen
import com.evo.navigation.BaseScreenModel

abstract class Profile<SM : BaseScreenModel<*>> : BaseScreen<SM, ProfileArgs>()

data class ProfileArgs(
    val sampleData: Int,
)
