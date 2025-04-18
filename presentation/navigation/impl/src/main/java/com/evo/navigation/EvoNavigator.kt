package com.evo.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.evo.navigation.NavPropertiesBuilder.NavigationPropertiesType.Default
import com.evo.navigation.NavPropertiesBuilder.NavigationPropertiesType.Replace
import com.evo.navigation.NavPropertiesBuilder.NavigationPropertiesType.ReplaceAll
import com.evo.navigation.PopPropertiesBuilder.PopPropertiesType.Pop
import com.evo.navigation.PopPropertiesBuilder.PopPropertiesType.PopAll
import com.evo.navigation.backstack.Backstack
import com.evo.navigation.backstack.BackstackHandler
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf


class EvoNavigator(
    private val backstackHandler: BackstackHandler,
) : EvoNavigationHandler {

    override fun navigate(screen: Screen, navBuilder: (NavPropertiesHandler) -> Unit) {
        val navProperties = NavPropertiesBuilder().apply(navBuilder)

        when (val type = navProperties.navPropertiesType) {
            Default -> backstackHandler.put(screen)
            is Replace -> backstackHandler.replaceScreens(screen, type.quantity)

            ReplaceAll -> backstackHandler.replaceAll(screen)
        }
    }

    override fun pop(popBuilder: (PopPropertiesHandler) -> Unit) {
        val popProperties = PopPropertiesBuilder().apply(popBuilder)

        when (val type = popProperties.popPropertiesType) {
            is Pop -> backstackHandler.removeScreens(type.quantity)
            PopAll -> backstackHandler.removeUntilFirst()
        }
    }

}

internal class EvoRootImpl : EvoRoot {
    @Composable
    override fun Content(initialScreen: Screen) {
        EvoRootImpl(initialScreen)
    }
}

@Composable
private fun EvoRootImpl(initialScreen: Screen) {
    val backstack = koinInject<Backstack> {
        parametersOf(initialScreen)
    }
    val evoNavigator = koinInject<EvoNavigationHandler>()

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        backstack.backstackState.last().Content(evoNavigator)
    }
}