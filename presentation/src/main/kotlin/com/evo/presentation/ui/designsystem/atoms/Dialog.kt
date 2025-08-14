package com.evo.presentation.ui.designsystem.atoms

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import com.evo.domain.extensions.Lambda
import com.evo.presentation.ui.designsystem.theme.DesignSystem
import com.evo.presentation.ui.designsystem.theme.background
import androidx.compose.ui.window.Dialog as MaterialDialog

@Composable
fun DesignSystem.Dialog(
    state: DialogState,
    content: @Composable () -> Unit,
) {
    if (state.isVisible) MaterialDialog(
        onDismissRequest = {
            state.closeDialog(true)
        },
        properties = DialogProperties(
            dismissOnBackPress = state.hardDismiss,
            dismissOnClickOutside = state.hardDismiss,
        ),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Colors.background.dialog, DesignSystem.Shapes.Small)
                    .padding(DesignSystem.Paddings.DSPx4),
                verticalArrangement = Arrangement.spacedBy(DesignSystem.Paddings.DSPx5),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                content()
            }
        },
    )
}

@Stable
class DialogState(
    isVisible: Boolean = false,
    private val onClose: (isHardDismiss: Boolean) -> Unit = {},
    private val onOpen: Lambda = {},
    internal val hardDismiss: Boolean = true,
) {

    internal var isVisible by mutableStateOf(isVisible)

    fun closeDialog(isHardDismiss: Boolean = false) {
        isVisible = false
        onClose(isHardDismiss)
    }

    fun openDialog() {
        onOpen()
        isVisible = true
    }
}

@Composable
fun rememberDialogState(
    isVisible: Boolean = false,
    onDismiss: (Boolean) -> Unit = {},
    onOpen: Lambda = {},
    hardDismiss: Boolean = true,
) = remember { DialogState(isVisible, onDismiss, onOpen, hardDismiss) }
