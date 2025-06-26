package com.evo.entrypoint

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.evo.domain.extensions.Lambda
import com.evo.presentation.ui.designsystem.atoms.*
import com.evo.presentation.ui.designsystem.theme.ClickInfo
import com.evo.presentation.ui.designsystem.theme.DesignSystem
import com.evo.resources.StringResources

@Composable
internal fun DialogState.DeleteConfirmationDialog(onConfirm: Lambda, onDeny: Lambda) {
    DesignSystem.Text(
        text = StringResources.AreYouSureWantToDelete.value,
        style = DesignSystem.TextStyles.title,
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            DesignSystem.Paddings.DSPx2,
            Alignment.End,
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        DesignSystem.ButtonInline(
            text = StringResources.Cancel.value,
            clickInfo = ClickInfo {
                onDeny()
                dismissDialog()
            },
        )
        DesignSystem.Button(
            text = StringResources.Confirm.value,
            clickInfo = ClickInfo {
                onConfirm()
                dismissDialog()
            },
        )
    }
}
