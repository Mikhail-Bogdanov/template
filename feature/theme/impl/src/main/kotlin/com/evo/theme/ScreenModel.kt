package com.evo.theme

import com.evo.navigation.BaseScreenModel

class ScreenModel(
    args: ThemeArgs,
): BaseScreenModel<State>() {

    override val state = State(args)

}