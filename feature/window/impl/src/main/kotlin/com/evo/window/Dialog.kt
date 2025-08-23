package com.evo.window

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.evo.presentation.ui.StringResources
import com.evo.presentation.ui.designsystem.atoms.Button
import com.evo.presentation.ui.designsystem.atoms.ButtonOutline
import com.evo.presentation.ui.designsystem.theme.*
import com.evo.presentation.ui.designsystem.theme.DesignSystem.Colors

context(EvoWindowFinisher)
@Composable
fun EvoDialog.Choice.ChoiceButtons() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(DesignSystem.Paddings.DSPx4),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        DesignSystem.ButtonOutline(
            modifier = Modifier.weight(1f),
            text = StringResources.No.value,
            clickInfo = ClickInfo {
                onNo()
                finishDialog()
            },
        )
        DesignSystem.Button(
            modifier = Modifier.weight(1f),
            text = StringResources.Yes.value,
            clickInfo = ClickInfo {
                onYes()
                finishDialog()
            },
        )
    }
}

context(EvoWindowFinisher)
@Composable
internal fun EvoDialog.Dialog(content: @Composable ColumnScope.() -> Unit) {
    Dialog(
        onDismissRequest = {
            onDismiss?.invoke()
            finishDialog()
        },
        properties = dialogProperties,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Colors.background.dialog, DesignSystem.Shapes.Medium)
                    .padding(DesignSystem.Paddings.DSPx4),
                verticalArrangement = Arrangement.spacedBy(DesignSystem.Paddings.DSPx4),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                content()
            }
        },
    )
}
