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
fun DesignSystem.Dialog(state: DialogState) {
    if (state.isVisible) MaterialDialog(
        onDismissRequest = {
            state.dismissDialog(true)
        },
        properties = DialogProperties(
            dismissOnBackPress = state.hardDismiss,
            dismissOnClickOutside = state.hardDismiss,
        ),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Colors.background.dialog, DesignSystem.Shapes.Element.Small)
                    .padding(DesignSystem.Paddings.DSPx4),
                verticalArrangement = Arrangement.spacedBy(DesignSystem.Paddings.DSPx5),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = state.content,
            )
        },
    )
}

@Stable
class DialogState(
    isVisible: Boolean = false,
    onDismiss: (Boolean) -> Unit = {},
    onOpen: Lambda = {},
    internal val hardDismiss: Boolean = true,
    content: @Composable ColumnScope.() -> Unit,
) {

    var onDismiss by mutableStateOf(onDismiss)
    var onOpen by mutableStateOf(onOpen)
    var content: @Composable ColumnScope.() -> Unit by mutableStateOf(content)
    var isVisible by mutableStateOf(isVisible)
        private set

    fun dismissDialog(isHardDismiss: Boolean = false) {
        isVisible = false
        onDismiss(isHardDismiss)
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
    content: @Composable ColumnScope.() -> Unit = {},
) = remember { DialogState(isVisible, onDismiss, onOpen, hardDismiss, content) }
