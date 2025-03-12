package com.evo.designsystem.atoms

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.evo.common.ClickInfo
import com.evo.common.ifNotNull
import com.evo.designsystem.theme.DesignSystem

@Composable
fun DesignSystem.LineItem(
    modifier: Modifier = Modifier,
    clickInfo: ClickInfo? = null,
    shape: Shape = shapes.small,
    colors: ListItemColors = primaryListItemColors(),
    border: BorderStroke? = null,
    overlineContent: @Composable (() -> Unit)? = null,
    supportingContent: @Composable (() -> Unit)? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit,
) = ListItem(
    headlineContent = content,
    modifier = modifier
        .ifNotNull(clickInfo) {
            clickable(onClick = it.onClick, enabled = it.enabled)
        }
        .ifNotNull(border) {
            border(border = it, shape = shape)
        }
        .clip(shape),
    overlineContent = overlineContent,
    supportingContent = supportingContent,
    leadingContent = leadingContent,
    trailingContent = trailingContent,
    colors = colors,
    tonalElevation = 0.dp,
    shadowElevation = 0.dp,
)

@Composable
fun primaryListItemColors(
    containerColor: Color = DesignSystem.colors.primary,
    headlineColor: Color = DesignSystem.colors.onPrimary,
    leadingIconColor: Color = DesignSystem.colors.onPrimary,
    overlineColor: Color = DesignSystem.colors.onPrimary,
    supportingTextColor: Color = DesignSystem.colors.onPrimary,
    trailingIconColor: Color = DesignSystem.colors.onPrimary,
    disabledHeadlineColor: Color = DesignSystem.colors.disabledOnPrimary,
    disabledLeadingIconColor: Color = DesignSystem.colors.disabledOnPrimary,
    disabledTrailingIconColor: Color = DesignSystem.colors.disabledOnPrimary,
) = ListItemColors(
    containerColor = containerColor,
    headlineColor = headlineColor,
    leadingIconColor = leadingIconColor,
    overlineColor = overlineColor,
    supportingTextColor = supportingTextColor,
    trailingIconColor = trailingIconColor,
    disabledHeadlineColor = disabledHeadlineColor,
    disabledLeadingIconColor = disabledLeadingIconColor,
    disabledTrailingIconColor = disabledTrailingIconColor,
)

@Composable
fun secondaryListItemColors(
    containerColor: Color = DesignSystem.colors.secondary,
    headlineColor: Color = DesignSystem.colors.onSecondary,
    leadingIconColor: Color = DesignSystem.colors.onSecondary,
    overlineColor: Color = DesignSystem.colors.onSecondary,
    supportingTextColor: Color = DesignSystem.colors.onSecondary,
    trailingIconColor: Color = DesignSystem.colors.onSecondary,
    disabledHeadlineColor: Color = DesignSystem.colors.disabledOnSecondary,
    disabledLeadingIconColor: Color = DesignSystem.colors.disabledOnSecondary,
    disabledTrailingIconColor: Color = DesignSystem.colors.disabledOnSecondary,
) = ListItemColors(
    containerColor = containerColor,
    headlineColor = headlineColor,
    leadingIconColor = leadingIconColor,
    overlineColor = overlineColor,
    supportingTextColor = supportingTextColor,
    trailingIconColor = trailingIconColor,
    disabledHeadlineColor = disabledHeadlineColor,
    disabledLeadingIconColor = disabledLeadingIconColor,
    disabledTrailingIconColor = disabledTrailingIconColor,
)

@Composable
fun customListItemColors(
    containerColor: Color,
    headlineColor: Color,
    leadingIconColor: Color,
    overlineColor: Color,
    supportingTextColor: Color,
    trailingIconColor: Color,
    disabledHeadlineColor: Color,
    disabledLeadingIconColor: Color,
    disabledTrailingIconColor: Color,
) = ListItemColors(
    containerColor = containerColor,
    headlineColor = headlineColor,
    leadingIconColor = leadingIconColor,
    overlineColor = overlineColor,
    supportingTextColor = supportingTextColor,
    trailingIconColor = trailingIconColor,
    disabledHeadlineColor = disabledHeadlineColor,
    disabledLeadingIconColor = disabledLeadingIconColor,
    disabledTrailingIconColor = disabledTrailingIconColor,
)