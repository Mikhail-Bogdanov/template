package com.evo.presentation.ui.designsystem.atoms.wrapper

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.evo.presentation.ui.conditional
import com.evo.presentation.ui.designsystem.atoms.Text
import com.evo.presentation.ui.designsystem.theme.DesignSystem

@Composable
fun WrapWithTitle(
    modifier: Modifier = Modifier,
    title: String?,
    style: TextStyle = DesignSystem.TextStyles.title,
    horizontal: Alignment.Horizontal = Alignment.Start,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(DesignSystem.Paddings.DSPx1),
        horizontalAlignment = horizontal,
    ) {
        title?.let {
            DesignSystem.Text(
                text = it,
                style = style,
                modifier = Modifier
                    .padding(bottom = DesignSystem.Paddings.DSPx1)
                    .conditional(horizontal == Alignment.Start) {
                        padding(start = DesignSystem.Paddings.DSPx1)
                    },
            )
        }
        content()
    }
}
