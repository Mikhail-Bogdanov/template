package com.evo.entrypoint

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.evo.presentation.ui.designsystem.atoms.*
import com.evo.presentation.ui.designsystem.atoms.bar.BottomBar
import com.evo.presentation.ui.designsystem.theme.DesignSystem
import com.evo.presentation.ui.designsystem.theme.MainAppTheme
import com.evo.screen.*
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainActivity : ComponentActivity(), KoinComponent {

    private val viewModel by viewModel<EntryPointViewModel> {
        val initialScreen by inject<EvoScreen>(named(Screens.MAIN_PAGE))
        parametersOf(initialScreen)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            with(viewModel) {
                val dialogState = rememberDialogState()

                LaunchedEffect(this) {
                    events.receiveAsFlow().collect { event ->
                        handleEvent(event, dialogState)
                    }
                }

                BackHandler(
                    enabled = dialogState.isVisible.not()
                ) {
                    pop()
                }

                MainAppTheme {
                    current.Content()

                    DesignSystem.Dialog(dialogState)

                    DesignSystem.BottomBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(DesignSystem.Paddings.DSPx3),
                        containerColor = DesignSystem.Colors.container.primary,
                        contentPadding = PaddingValues(DesignSystem.Paddings.DSPx2),
                        shape = DesignSystem.Shapes.Bar.Bottom.Floating.Big,
                        actions = persistentListOf(

                        ),
                    )
                }
            }
        }
    }

    private suspend fun handleEvent(event: EvoEvent, dialogState: DialogState) {
        suspendCoroutine { cont ->
            when (event) {
                is EvoEvent.Confirmation.Delete -> {
                    when (val type = event.type) {
                        EvoEventType.Dialog -> {
                            dialogState.apply {
                                onDismiss = { isHardDismiss ->
                                    if (isHardDismiss) event.onDeny()
                                    try {
                                        cont.resume(Unit)
                                    } catch (e: Exception) {
                                        Log.d("TAG228", "Handle event: ${e.localizedMessage}")
                                    }
                                }
                                content = {
                                    DeleteConfirmationDialog(
                                        onConfirm = event.onConfirm,
                                        onDeny = event.onDeny,
                                    )
                                }
                                openDialog()
                            }
                        }

                        EvoEventType.SnackBar -> {}

                        is EvoEventType.EvoToast -> type.toast.show()
                    }
                }

                EvoEvent.Exit -> {
                    finish()
                }

                is EvoEvent.Undo -> {}

                is EvoEvent.Info.Copied -> {
                    when (val type = event.type) {
                        EvoEventType.Dialog -> {}

                        EvoEventType.SnackBar -> {}

                        is EvoEventType.EvoToast -> type.toast.show()
                    }
                }
            }
        }
    }

}
