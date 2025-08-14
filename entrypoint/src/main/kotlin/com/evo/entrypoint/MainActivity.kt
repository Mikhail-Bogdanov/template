package com.evo.entrypoint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.evo.navigation.*
import com.evo.presentation.ui.designsystem.atoms.bar.BottomBar
import com.evo.presentation.ui.designsystem.theme.DesignSystem
import com.evo.presentation.ui.designsystem.theme.DesignSystem.Shapes.ExtraLarge.asTopBar
import com.evo.presentation.ui.designsystem.theme.MainAppTheme
import kotlinx.collections.immutable.persistentListOf
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

class MainActivity : ComponentActivity(), KoinComponent, AppExitHandler {

    private val viewModel by viewModel<EntryPointViewModel>()
    private val backstack: Backstack by inject()
    private val navigator: EvoNavigator by inject()

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

            val screen = backstack.lastScreenFlow.collectAsStateWithLifecycle().value

            BackHandler {
                navigator.pop()
            }

            MainAppTheme {
                screen.Content()

                DesignSystem.BottomBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(DesignSystem.Paddings.DSPx3),
                    containerColor = DesignSystem.Colors.container.primary,
                    contentPadding = PaddingValues(DesignSystem.Paddings.DSPx2),
                    shape = DesignSystem.Shapes.Big.asTopBar(),
                    actions = persistentListOf(

                    ),
                )
            }
        }
    }
}
