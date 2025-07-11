package com.evo.signup

import com.evo.screen.BaseScreenModel

class ScreenModel(
    args: SignUpArgs,
) : BaseScreenModel<State>() {

    override val state = State(args)

}
