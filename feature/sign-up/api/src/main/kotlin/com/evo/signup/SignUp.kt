package com.evo.signup

import com.evo.navigation.BaseScreen
import com.evo.navigation.BaseScreenModel

abstract class SignUp <SM : BaseScreenModel<*>> : BaseScreen<SM>()

data class SignUpArgs(
    val sampleData: Int,
)