package com.evo.navigation

import androidx.compose.runtime.Immutable
import com.evo.logger.EvoLogger
import com.evo.logger.Loggable
import com.evo.presentation.ui.designsystem.atoms.TabDSIcon
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

interface ArgsOwner<ARGS : Any> {

    val args: ARGS
}

@Immutable
abstract class ActiveScreenHandler<SM : EvoScreenModel> internal constructor() : Loggable,
    EvoLifecycleOwner, ScreenModelOwner<SM>, KoinComponent {

    protected val navigator: EvoNavigator by inject()
    override val evoLogger: EvoLogger by inject()

    final override fun onEnter() {
        screenModel.isScreenActive = true
    }

    final override fun onExit() {
        screenModel.isScreenActive = false
        screenModel.clear()
    }

    final override fun onReturn() {
        screenModel.isScreenActive = true
    }
}

inline fun <reified SCREEN, reified ARGS : Any> ActiveScreenHandler<*>.screen(
    args: ARGS,
): SCREEN where SCREEN : ActiveScreenHandler<*>, SCREEN : ArgsOwner<ARGS> {
    val screen: SCREEN by inject { parametersOf(args) }
    return screen
}

inline fun <reified SCREEN : ActiveScreenHandler<*>> ActiveScreenHandler<*>.screen(): SCREEN {
    val screen: SCREEN by inject()
    return screen
}

@Immutable
abstract class BaseScreen<SM : EvoScreenModel> : ActiveScreenHandler<SM>(), EvoScreen

@Immutable
abstract class BaseTab<SM : EvoScreenModel> : ActiveScreenHandler<SM>(), EvoTab {

    abstract val tabIcon: TabDSIcon
}
