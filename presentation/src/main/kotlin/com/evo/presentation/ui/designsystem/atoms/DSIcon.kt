package com.evo.presentation.ui.designsystem.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.evo.presentation.ui.designsystem.theme.*
import com.evo.presentation.ui.designsystem.theme.DesignSystem.Shapes
import com.evo.presentation.ui.ifNotNull
import com.evo.presentation.ui.IconResource
import androidx.compose.material3.Icon as MaterialIcon

enum class IconSize(val dp: Dp) {
    Big(48.dp), Medium(36.dp), Small(24.dp),
}

@Composable
fun DesignSystem.Icon(
    modifier: Modifier = Modifier,
    size: IconSize = IconSize.Big,
    icon: DSIcon,
) {
    Column(
        modifier = modifier
            .defaultMinSize(minWidth = size.dp, minHeight = size.dp)
            .clip(icon.shape)
            .ifNotNull(
                value = icon.clickInfo,
                operation = {
                    background(
                        if (it.enabled) {
                            icon.colors.containerColor
                        } else {
                            icon.colors.disabledContainerColor
                        }
                    )
                },
                elseOperation = {
                    background(icon.colors.containerColor)
                },
            )
            .ifNotNull(icon.clickInfo) {
                clickable(enabled = it.enabled, onClick = it.onClick)
            }
            .padding(DesignSystem.Paddings.DSPx2),
        verticalArrangement = Arrangement.spacedBy(
            DesignSystem.Paddings.DSPx1,
            Alignment.CenterVertically,
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val isEnabled by remember {
            derivedStateOf { icon.clickInfo == null || icon.clickInfo.enabled }
        }

        MaterialIcon(
            modifier = Modifier.size(size.dp / 2),
            imageVector = ImageVector.vectorResource(icon.resource.res),
            contentDescription = null,
            tint = if (isEnabled) icon.colors.contentColor else icon.colors.disabledContentColor,
        )

        icon.text?.let {
            Text(
                text = it,
                style = DesignSystem.TextStyles.description,
                color = if (isEnabled) icon.colors.contentColor else icon.colors.disabledContentColor,
            )
        }
    }
}

@Immutable
data class DSIcon(
    val resource: IconResource,
    val text: String? = null,
    val shape: Shapes = Shapes.Medium,
    val colors: IconColors,
    val clickInfo: ClickInfo?,
)

@Immutable
data class TabDSIcon(
    val resource: IconResource,
    val text: String? = null,
)

@Immutable
data class IconColors(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
)

@Composable
fun DesignSystem.primaryIconColors(
    contentColor: Color = Colors.content.primary,
    containerColor: Color = Colors.container.primary,
    disabledContentColor: Color = Colors.content.disabled,
    disabledContainerColor: Color = Colors.container.disabled,
) = IconColors(
    contentColor = contentColor,
    containerColor = containerColor,
    disabledContentColor = disabledContentColor,
    disabledContainerColor = disabledContainerColor,
)

@Composable
fun DesignSystem.secondaryIconColors(
    contentColor: Color = Colors.content.secondary,
    containerColor: Color = Colors.container.secondary,
    disabledContentColor: Color = Colors.content.disabled,
    disabledContainerColor: Color = Colors.container.disabled,
) = IconColors(
    contentColor = contentColor,
    containerColor = containerColor,
    disabledContentColor = disabledContentColor,
    disabledContainerColor = disabledContainerColor,
)

@Composable
fun DesignSystem.highlightIconColors(
    contentColor: Color = Colors.content.highlight,
    disabledContentColor: Color = Colors.content.disabled,
    containerColor: Color = EmptyColor,
    disabledContainerColor: Color = EmptyColor,
) = IconColors(
    contentColor = contentColor,
    containerColor = containerColor,
    disabledContentColor = disabledContentColor,
    disabledContainerColor = disabledContainerColor,
)

@Composable
fun DesignSystem.customIconColors(
    contentColor: Color,
    containerColor: Color,
    disabledContentColor: Color,
    disabledContainerColor: Color,
) = IconColors(
    contentColor = contentColor,
    containerColor = containerColor,
    disabledContentColor = disabledContentColor,
    disabledContainerColor = disabledContainerColor,
)

@Composable
fun DesignSystem.onlyIconColors(contentColor: Color = Colors.content.primary) = IconColors(
    contentColor = contentColor,
    containerColor = DesignSystem.EmptyColor,
    disabledContentColor = Colors.content.disabled,
    disabledContainerColor = DesignSystem.EmptyColor,
)
