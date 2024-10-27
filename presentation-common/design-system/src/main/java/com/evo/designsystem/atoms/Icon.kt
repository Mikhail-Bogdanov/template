package com.evo.designsystem.atoms

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.evo.designsystem.theme.DesignSystem
import com.evo.extensions.ClickInfo
import com.evo.extensions.ifNotNullValue
import androidx.compose.material3.Icon as MaterialIcon


@Composable
fun DesignSystem.Icon(
    modifier: Modifier = Modifier,
    icon: Icon,
) = Box(
    modifier = modifier
        .size(48.dp)
        .clip(icon.shape)
        .ifNotNullValue(
            value = icon.clickInfo,
            modifier = {
                background(
                    if (it.enabled) {
                        icon.colors.containerColor
                    } else {
                        icon.colors.disabledContainerColor
                    }
                )
            },
            elseModifier = {
                background(icon.colors.containerColor)
            },
        )
        .ifNotNullValue(icon.clickInfo) {
            clickable(enabled = it.enabled, onClick = it.onClick)
        },
    contentAlignment = Alignment.Center
) {
    val isEnabled by remember {
        derivedStateOf { icon.clickInfo?.enabled == true || icon.clickInfo == null }
    }

    MaterialIcon(
        imageVector = ImageVector.vectorResource(icon.iconResource),
        contentDescription = null,
        tint = if (isEnabled) icon.colors.contentColor else icon.colors.disabledContentColor
    )
}

@Stable
data class Icon(
    @DrawableRes val iconResource: Int,
    val shape: Shape = DesignSystem.shapes.medium,
    val colors: IconColors,
    val clickInfo: ClickInfo? = null,
)

@Immutable
data class IconColors(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
)

@Composable
fun iconPrimaryColors(
    contentColor: Color = DesignSystem.colors.onPrimary,
    containerColor: Color = DesignSystem.colors.primary,
    disabledContentColor: Color = DesignSystem.colors.disabledOnPrimary,
    disabledContainerColor: Color = DesignSystem.colors.disabledPrimary,
) = IconColors(
    contentColor = contentColor,
    containerColor = containerColor,
    disabledContentColor = disabledContentColor,
    disabledContainerColor = disabledContainerColor,
)

@Composable
fun iconSecondaryColors(
    contentColor: Color = DesignSystem.colors.onSecondary,
    containerColor: Color = DesignSystem.colors.secondary,
    disabledContentColor: Color = DesignSystem.colors.disabledOnSecondary,
    disabledContainerColor: Color = DesignSystem.colors.disabledSecondary,
) = IconColors(
    contentColor = contentColor,
    containerColor = containerColor,
    disabledContentColor = disabledContentColor,
    disabledContainerColor = disabledContainerColor,
)

@Composable
fun iconCustomColors(
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