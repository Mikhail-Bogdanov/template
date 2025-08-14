package com.evo.signup

import com.evo.navigation.BaseScreenModel

class ScreenModel(
    args: SignUpArgs,
): BaseScreenModel<State>() {

    override val state = State(args)

}