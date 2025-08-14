package com.evo.locale

import com.evo.navigation.BaseScreenModel

class ScreenModel(
    args: LocaleArgs,
): BaseScreenModel<State>() {

    override val state = State(args)

}