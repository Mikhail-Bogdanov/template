package com.evo.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.evo.navigation.NavPropertiesBuilder.NavigationPropertiesType.Default
import com.evo.navigation.NavPropertiesBuilder.NavigationPropertiesType.Replace
import com.evo.navigation.NavPropertiesBuilder.NavigationPropertiesType.ReplaceAll
import com.evo.navigation.PopPropertiesBuilder.PopPropertiesType.Pop
import com.evo.navigation.PopPropertiesBuilder.PopPropertiesType.PopAll
import com.evo.navigation.api.BackstackHandler
import com.evo.navigation.api.EvoNavigationHandler
import com.evo.navigation.api.NavPropertiesHandler
import com.evo.navigation.api.PopPropertiesHandler


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

internal class EvoNavigationEngine internal constructor(
    private val backstack: Backstack,
    evoNavigator: EvoNavigator,
) {
    val lastScreen: Screen
        @Composable
        get() = backstack.backstackState.last()

    val navigationHandler: EvoNavigationHandler = evoNavigator
}

@Composable
internal fun rememberNavigationEngine(
    initialScreen: Screen,
): EvoNavigationEngine {
    val backstack = Backstack(initialScreen)
    return remember {
        EvoNavigationEngine(
            backstack = Backstack(initialScreen),
            evoNavigator = EvoNavigator(backstack)
        )
    }
}

@Composable
fun EvoRoot(initialScreen: Screen) {
    val navigationEngine = rememberNavigationEngine(initialScreen)

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        navigationEngine.lastScreen.Content(navigationEngine.navigationHandler)
    }
}