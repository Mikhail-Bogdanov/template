package com.evo.login

import com.evo.navigation.BaseScreenModel

class ScreenModel(
    args: LoginArgs,
): BaseScreenModel<State>() {

    override val state = State(args)

}