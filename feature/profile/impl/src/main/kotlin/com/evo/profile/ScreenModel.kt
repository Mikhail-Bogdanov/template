package com.evo.profile

import com.evo.navigation.BaseScreenModel

class ScreenModel(
    args: ProfileArgs,
): BaseScreenModel<State>() {

    override val state = State(args)

}