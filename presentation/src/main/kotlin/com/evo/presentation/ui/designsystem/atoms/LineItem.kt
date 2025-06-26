package com.evo.presentation.ui.designsystem.atoms

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.evo.presentation.ui.designsystem.theme.*
import com.evo.presentation.ui.ifNotNull

@Composable
fun DesignSystem.LineItem(
    modifier: Modifier = Modifier,
    title: CharSequence,
    clickInfo: ClickInfo? = null,
    titleMaxLines: Int = 1,
    shape: DesignSystem.Shapes = DesignSystem.Shapes.Medium,
    isError: Boolean = false,
    textAlign: TextAlign = TextAlign.Start,
    colors: LineItemColors = primaryLineItemColors(),
    border: BorderStroke? = null,
    supportingText: String? = null,
    overlineText: String? = null,
    leadingIcon: DSIcon? = null,
    trailingIcon: DSIcon? = null,
) {
    val finalColors = if (isError) dangerLineItemColors() else colors
    ListItem(
        headlineContent = {
            Text(
                text = title,
                color = finalColors.titleColor,
                style = when (titleMaxLines) {
                    1 -> DesignSystem.TextStyles.title
                    2 -> DesignSystem.TextStyles.body
                    else -> DesignSystem.TextStyles.description
                },
                softWrap = titleMaxLines != 1,
                maxLines = titleMaxLines,
                textAlign = textAlign,
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .ifNotNull(clickInfo) {
                clickable(onClick = it.onClick, enabled = it.enabled)
            }
            .ifNotNull(border) {
                border(border = it, shape = shape)
            },
        overlineContent = overlineText?.let {
            {
                Text(
                    text = it,
                    color = finalColors.titleColor.alphaDecreased(),
                    style = DesignSystem.TextStyles.body,
                )
            }
        },
        supportingContent = supportingText?.let {
            {
                Text(
                    text = it,
                    color = finalColors.titleColor.alphaDecreased(),
                    style = DesignSystem.TextStyles.description,
                )
            }
        },
        leadingContent = leadingIcon?.let {
            {
                val finalLeadingIcon = it.copy(
                    colors = IconColors(
                        contentColor = finalColors.titleColor,
                        disabledContentColor = finalColors.titleColor,
                        containerColor = EmptyColor,
                        disabledContainerColor = EmptyColor
                    )
                )
                Icon(icon = finalLeadingIcon)
            }
        },
        trailingContent = trailingIcon?.let {
            {
                val finalTrailingIcon = it.copy(
                    colors = IconColors(
                        contentColor = finalColors.titleColor,
                        disabledContentColor = finalColors.titleColor,
                        containerColor = EmptyColor,
                        disabledContainerColor = EmptyColor
                    )
                )
                Icon(icon = finalTrailingIcon)
            }
        },
        colors = finalColors.toMaterialLineItemColors(),
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
    )
}

data class LineItemColors(
    val titleColor: Color,
    val containerColor: Color,
)

@Composable
fun DesignSystem.primaryLineItemColors(
    titleColor: Color = Colors.content.primary,
    containerColor: Color = Colors.container.primary,
) = LineItemColors(
    titleColor = titleColor,
    containerColor = containerColor,
)

@Composable
fun DesignSystem.dangerLineItemColors(
    titleColor: Color = Colors.content.danger,
    containerColor: Color = Colors.container.danger,
) = LineItemColors(
    titleColor = titleColor,
    containerColor = containerColor,
)

@Composable
fun DesignSystem.secondaryLineItemColors(
    titleColor: Color = Colors.content.primary,
    containerColor: Color = Colors.container.secondary,
) = LineItemColors(
    titleColor = titleColor,
    containerColor = containerColor,
)

@Composable
fun DesignSystem.customLineItemColors(
    containerColor: Color,
    titleColor: Color,
) = LineItemColors(
    titleColor = titleColor,
    containerColor = containerColor,
)

@Composable
private fun LineItemColors.toMaterialLineItemColors() = ListItemColors(
    headlineColor = titleColor,
    containerColor = containerColor,
    supportingTextColor = DesignSystem.EmptyColor,
    leadingIconColor = DesignSystem.EmptyColor,
    trailingIconColor = DesignSystem.EmptyColor,
    overlineColor = DesignSystem.EmptyColor,
    disabledHeadlineColor = DesignSystem.EmptyColor,
    disabledLeadingIconColor = DesignSystem.EmptyColor,
    disabledTrailingIconColor = DesignSystem.EmptyColor,
)
