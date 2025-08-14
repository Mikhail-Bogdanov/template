package com.evo.settings

import com.evo.navigation.BaseScreenModel

class ScreenModel(
    args: SettingsArgs,
): BaseScreenModel<State>() {

    override val state = State(args)

}