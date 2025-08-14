package com.evo.navigation

import androidx.compose.runtime.Immutable
import com.evo.di.EvoScopeModuleOwner
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

@Immutable
abstract class BaseScreen<SM : BaseScreenModel<*>, ARGS> : EvoScopeModuleOwner(),
    ScreenLifecycleOwner, EvoScreen {

    abstract val screenModel: SM

    protected val navigator: EvoNavigator by inject()
    private val appExitHandler: AppExitHandler by inject()
    private val activeScreenFlowHandler: ActiveScreenFlowHandler by inject()

    private val isActiveFlow = activeScreenFlowHandler.observerIsScreenActive(this).onEach {
            onActivityChanged(it)
            screenModel.isScreenActive = it
        }.launchIn(screenModel.scope)

    open fun onActivityChanged(isActive: Boolean) {}

    fun exit() = appExitHandler.exit()

    protected inline fun <reified SCREEN : BaseScreen<*, ARGS>, reified ARGS> screen(args: ARGS): SCREEN {
        val screen: SCREEN by inject { parametersOf(args) }
        return screen
    }

    protected inline fun <reified SCREEN : BaseScreen<*, Unit>> screen(): SCREEN {
        val screen: SCREEN by inject()
        return screen
    }
}
