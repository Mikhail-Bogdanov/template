@file:OptIn(ExperimentalUuidApi::class)

package com.evo.window

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.evo.coroutine.ScopeProvider
import com.evo.domain.extensions.suspendUntilNull
import com.evo.logger.*
import com.evo.presentation.ui.StringResources
import com.evo.presentation.ui.designsystem.atoms.*
import com.evo.presentation.ui.designsystem.theme.ClickInfo
import com.evo.presentation.ui.designsystem.theme.DesignSystem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.uuid.ExperimentalUuidApi

internal class EvoWindowImpl(
    override val evoLogger: EvoLogger,
    evoWindowHandler: EvoWindowHandler,
    scopeProvider: ScopeProvider,
) : EvoWindow, Loggable, EvoWindowFinisher {

    private val scope = scopeProvider.provideIO()
    private val currentSnackFlow = MutableStateFlow<EvoSnack?>(null)
    private val currentDialogFlow = MutableStateFlow<EvoDialog?>(null)

    init {
        evoWindowHandler.snackChannel.receiveAsFlow().suspendUntilNull(currentSnackFlow)
            .onEach { snack ->
                currentSnackFlow.value = snack
                scope.launch {
                    delay(snack.duration)
                    finishSnack(snack.id)
                }
                logi(snack)
            }.launchIn(scope)

        evoWindowHandler.dialogChannel.onEach {
            currentDialogFlow.value = it
            logi(it)
        }.launchIn(scope)
    }

    @Composable
    override fun DialogContent() {
        val currentEvent by currentDialogFlow.collectAsStateWithLifecycle()

        currentEvent?.let {
            it.Dialog {
                when (it) {
                    is EvoDialog.Choice.Action -> {
                        DesignSystem.Text(
                            text = it.content.value,
                            modifier = Modifier.fillMaxWidth(),
                            style = DesignSystem.TextStyles.title,
                        )
                        it.ChoiceButtons()
                    }

                    is EvoDialog.Choice.Exit -> {
                        DesignSystem.Text(
                            text = StringResources.AreYouSureWantToExit.value,
                            modifier = Modifier.fillMaxWidth(),
                            style = DesignSystem.TextStyles.title,
                        )
                        it.ChoiceButtons()
                    }

                    is EvoDialog.Info -> {
                        DesignSystem.Text(
                            text = it.text.value,
                            modifier = Modifier.fillMaxWidth(),
                            style = DesignSystem.TextStyles.title,
                        )
                        DesignSystem.Button(
                            text = StringResources.Continue.value,
                            clickInfo = ClickInfo {
                                it.onContinue()
                                finishDialog()
                            },
                        )
                    }

                    is EvoDialog.Custom -> it.content
                }
            }
        }
    }

    @Composable
    override fun SnackContent() {
        val currentEvent by currentSnackFlow.collectAsStateWithLifecycle()

        currentEvent?.let {
            when (it) {
                is EvoSnack.Info -> it.BaseSnack(
                    supportingText = it.supportingText,
                    trailingContent = it.trailingIcon?.let { { DesignSystem.Icon(icon = it) } },
                    onClick = it.onClick,
                )

                is EvoSnack.Undo -> it.BaseSnack(
                    trailingContent = {
                        DesignSystem.ButtonInline(
                            text = StringResources.Undo.value,
                            clickInfo = ClickInfo {
                                it.onUndo()
                                finishSnack(it.id)
                            },
                            contentPadding = PaddingValues(
                                horizontal = DesignSystem.Paddings.DSPx4,
                                vertical = DesignSystem.Paddings.DSPx2,
                            ),
                        )
                    },
                )
            }
        }
    }

    override fun finishDialog() {
        currentDialogFlow.value = null
    }

    override fun finishSnack(snackId: EvoSnackId) {
        val currentSnack = currentSnackFlow.value
        if (currentSnack?.id == snackId) currentSnackFlow.value = null
    }
}

interface EvoWindowFinisher {

    fun finishDialog()
    fun finishSnack(snackId: EvoSnackId)
}
