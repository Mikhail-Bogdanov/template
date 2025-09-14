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
import androidx.lifecycle.lifecycleScope
import com.evo.bottombar.BottomBar
import com.evo.coroutine.ScopeProvider
import com.evo.navigation.*
import com.evo.presentation.ui.designsystem.theme.DesignSystem
import com.evo.presentation.ui.designsystem.theme.MainAppTheme
import com.evo.presentation.ui.share
import com.evo.topbar.*
import com.evo.window.EvoWindow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.compose.koinInject
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

class MainActivity : ComponentActivity(), KoinComponent {

    private val scopeProvider: ScopeProvider by inject()
    private val initialScreenHandler: InitialScreenHandler by inject()
    private val evoWindow: EvoWindow by inject()
    private val appExitHandler: AppExitHandler by inject()
    private val viewModel: EntryPointViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        appExitHandler.exitCommandFlow.onEach {
            finish()
        }.launchIn(lifecycleScope)

        // blocks Main thread to retrieve initial screen
        val initialEvoScreen = runBlocking { initialScreenHandler.retrieve() }

        setContent {
            val backstack: Backstack = koinInject {
                parametersOf(initialEvoScreen)
            }
            val navigator: EvoNavigator = koinInject()
            val screen = backstack.lastScreenFlow.collectAsStateWithLifecycle().value

            BackHandler {
                navigator.pop()
            }

            CompositionLocalProvider(
                LocalNavigator provides navigator,
            ) {
                MainAppTheme {
                    evoWindow.DialogContent()
                    Scaffold(
                        bottomBar = {
                            if (screen is BaseTab) {
                                share<BottomBar, BaseTab>(screen)
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
                                .fillMaxSize()
                                .padding(paddingValues),
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
