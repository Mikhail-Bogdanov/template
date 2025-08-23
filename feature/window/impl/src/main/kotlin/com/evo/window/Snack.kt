@file:OptIn(ExperimentalUuidApi::class)

package com.evo.window

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.evo.domain.extensions.Lambda
import com.evo.presentation.ui.StringResources
import com.evo.presentation.ui.designsystem.atoms.Icon
import com.evo.presentation.ui.designsystem.atoms.Text
import com.evo.presentation.ui.designsystem.theme.DesignSystem
import com.evo.presentation.ui.designsystem.theme.clip
import com.evo.presentation.ui.ifNotNull
import kotlin.uuid.ExperimentalUuidApi

context(EvoWindowFinisher)
@Composable
fun EvoSnack.BaseSnack(
    supportingText: StringResources? = null,
    trailingContent: @Composable Lambda?,
    onClick: Lambda? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(DesignSystem.Shapes.Small)
            .ifNotNull(onClick) {
                clickable {
                    it()
                    finishSnack(id)
                }
            }
            .background(DesignSystem.Colors.container.primary)
            .padding(DesignSystem.Paddings.DSPx2),
        horizontalArrangement = Arrangement.spacedBy(DesignSystem.Paddings.DSPx4),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(DesignSystem.Paddings.DSPx2),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                leadingIcon?.let { DesignSystem.Icon(icon = it) }
                DesignSystem.Text(
                    text = text.value,
                    style = DesignSystem.TextStyles.title,
                )
            }
            supportingText?.let {
                DesignSystem.Text(
                    text = it.value,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
        trailingContent?.invoke()
    }
}
