package com.evo.permission

import com.evo.navigation.BaseScreenModel

class ScreenModel(
    args: PermissionArgs,
): BaseScreenModel<State>() {

    override val state = State(args)

}