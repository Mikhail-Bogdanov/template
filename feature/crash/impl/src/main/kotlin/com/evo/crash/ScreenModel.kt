package com.evo.crash

import com.evo.navigation.BaseScreenModel

class ScreenModel(
    args: CrashArgs,
): BaseScreenModel<State>() {

    override val state = State(args)

}