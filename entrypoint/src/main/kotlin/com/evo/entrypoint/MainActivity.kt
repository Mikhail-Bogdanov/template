package com.evo.entrypoint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.evo.bottombar.BottomBar
import com.evo.coroutine.ScopeProvider
import com.evo.navigation.*
import com.evo.presentation.ui.designsystem.theme.DesignSystem
import com.evo.presentation.ui.designsystem.theme.MainAppTheme
import com.evo.presentation.ui.share
import com.evo.topbar.*
import com.evo.window.EvoWindow
import org.koin.compose.koinInject
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

class MainActivity : ComponentActivity(), KoinComponent, AppExitHandler {

    private val scopeProvider: ScopeProvider by inject()
    private val initialScreenHandler: InitialScreenHandler by inject()
    private val evoWindow: EvoWindow by inject()

    override fun exit() {
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val textMeasurer = rememberTextMeasurer()

            DisposableEffect(Unit) {
                val module = module {
                    factory { textMeasurer }
                }
                loadKoinModules(module)
                onDispose {
                    unloadKoinModules(module)
                }
            }

            val viewModel: EntryPointViewModel = viewModel()
            val backstack: Backstack = koinInject {
                parametersOf(initialScreenHandler.get(), this@MainActivity)
            }
            val navigator: EvoNavigator = koinInject()
            val screen = backstack.lastScreenFlow.collectAsStateWithLifecycle().value

            BackHandler {
                navigator.pop()
            }

            CompositionLocalProvider(
                LocalNavigator provides navigator,
                LocalExitHandler provides this,
            ) {
                MainAppTheme {
                    evoWindow.DialogContent()
                    Scaffold(
                        bottomBar = {
                            if (screen is BaseTab<*>) {
                                share<BottomBar, BaseTab<*>>(screen)
                            }
                        },
                        containerColor = DesignSystem.Colors.background.level0,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(DesignSystem.Colors.background.level0),
                        topBar = {
                            if (screen is TopBarOwner) {
                                share<TopBar, TopBarArgs>(screen.topBarArgs)
                            }
                        },
                        snackbarHost = {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(DesignSystem.Paddings.DSPx3),
                                contentAlignment = Alignment.Center,
                            ) {
                                evoWindow.SnackContent()
                            }
                        },
                    ) { paddingValues ->
                        Box(
                            modifier = Modifier
                                .padding(paddingValues)
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            screen.Content()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scopeProvider.cancel()
    }
}
