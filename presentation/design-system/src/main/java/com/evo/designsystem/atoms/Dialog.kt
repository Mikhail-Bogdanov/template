package com.evo.designsystem.atoms

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.DialogProperties
import com.evo.designsystem.theme.DesignSystem
import androidx.compose.ui.window.Dialog as MaterialDialog

@Composable
fun DesignSystem.Dialog(
    dialogState: DialogState,
    hardDismiss: Boolean = true,
    content: @Composable () -> Unit,
) {
    if (dialogState.isVisible) MaterialDialog(
        onDismissRequest = {
            dialogState.dismissDialog()
        },
        properties = DialogProperties(
            dismissOnBackPress = hardDismiss,
            dismissOnClickOutside = hardDismiss
        ),
        content = content
    )
}

@Stable
class DialogState(
    isVisible: Boolean,
) {
    var isVisible by mutableStateOf(isVisible)
        private set

    fun dismissDialog() {
        isVisible = false
    }

    fun openDialog() {
        isVisible = true
    }
}

@Composable
fun rememberDialogState(isVisible: Boolean = false) = remember {
    mutableStateOf(DialogState(isVisible))
}