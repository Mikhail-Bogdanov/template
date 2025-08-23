@file:OptIn(ExperimentalUuidApi::class)

package com.evo.window

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import com.evo.domain.extensions.Lambda
import com.evo.presentation.ui.StringResources
import com.evo.presentation.ui.designsystem.atoms.DSIcon
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow
import kotlin.time.*
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface EvoWindow {

    @Composable
    fun DialogContent()

    @Composable
    fun SnackContent()

}

interface EvoWindowHandler {

    val dialogChannel: Flow<EvoDialog?>
    val snackChannel: ReceiveChannel<EvoSnack>

    suspend fun showSnack(snack: EvoSnack)

    suspend fun showDialog(dialog: EvoDialog)

}

sealed interface EvoWindowEvent

typealias EvoSnackId = Uuid

sealed class EvoSnack : EvoWindowEvent {

    val id: EvoSnackId = Uuid.random()

    abstract val text: StringResources
    abstract val duration: Duration
    abstract val leadingIcon: DSIcon?

    data class Info(
        override val text: StringResources,
        override val duration: Duration = 3.toDuration(DurationUnit.SECONDS),
        override val leadingIcon: DSIcon? = null,
        val trailingIcon: DSIcon? = null,
        val onClick: Lambda? = null,
        val supportingText: StringResources? = null,
    ) : EvoSnack()

    data class Undo(
        override val text: StringResources,
        override val duration: Duration = 5.toDuration(DurationUnit.SECONDS),
        override val leadingIcon: DSIcon? = null,
        val onUndo: Lambda,
    ) : EvoSnack()
}

sealed interface EvoDialog : EvoWindowEvent {

    val onDismiss: Lambda?
    val dialogProperties: DialogProperties

    sealed class Choice(val value: StringResources) : EvoDialog {

        abstract val onYes: Lambda
        abstract val onNo: Lambda

        data class Exit(
            override val onYes: Lambda,
            override val onNo: Lambda = {},
            override val onDismiss: Lambda = onNo,
        ) : Choice(StringResources.Exit) {

            override val dialogProperties = softDialogProperties
        }

        data class Action(
            override val onYes: Lambda,
            override val onNo: Lambda = {},
            override val onDismiss: Lambda = onNo,
            val content: StringResources,
        ) : Choice(StringResources.Cancel) {

            override val dialogProperties = softDialogProperties
        }
    }

    data class Info(
        override val onDismiss: Lambda? = null,
        val text: StringResources,
        val onContinue: Lambda,
    ) : EvoDialog {

        override val dialogProperties = softDialogProperties
    }

    data class Custom(
        override val onDismiss: Lambda? = null,
        override val dialogProperties: DialogProperties = softDialogProperties,
        val content: @Composable ColumnScope.() -> Unit,
    ) : EvoDialog

    companion object {

        val softDialogProperties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
        )

        val hardDialogProperties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
        )
    }

}
