package com.evo.login

import com.evo.navigation.BaseScreen
import com.evo.navigation.BaseScreenModel

abstract class Login <SM : BaseScreenModel<*>> : BaseScreen<SM>()

data class LoginArgs(
    val sampleData: Int,
)