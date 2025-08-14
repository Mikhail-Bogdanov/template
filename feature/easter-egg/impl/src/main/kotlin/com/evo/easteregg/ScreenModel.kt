package com.evo.easteregg

import com.evo.navigation.BaseScreenModel

class ScreenModel(
    args: EasterEggArgs,
): BaseScreenModel<State>() {

    override val state = State(args)

}