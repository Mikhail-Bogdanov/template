package com.evo.designsystem.atoms

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.evo.common.ClickInfo
import com.evo.designsystem.theme.DesignSystem
import androidx.compose.material3.Button as MaterialButton

@Composable
fun DesignSystem.Button(
    modifier: Modifier = Modifier,
    text: String,
    shape: Shape = shapes.medium,
    colors: ButtonColors = buttonPrimaryColors(),
    border: BorderStroke? = null,
    clickInfo: ClickInfo,
) = MaterialButton(
    modifier = modifier,
    onClick = clickInfo.onClick,
    content = { Text(text = text, color = colors.contentColor, style = textStyles.button) },
    enabled = clickInfo.enabled,
    shape = shape,
    colors = colors,
    border = border,
)

@Composable
fun DesignSystem.Button(
    modifier: Modifier = Modifier,
    shape: Shape = shapes.medium,
    colors: ButtonColors = buttonPrimaryColors(),
    border: BorderStroke? = null,
    clickInfo: ClickInfo,
    content: @Composable RowScope.() -> Unit,
) = MaterialButton(
    modifier = modifier,
    onClick = clickInfo.onClick,
    content = content,
    enabled = clickInfo.enabled,
    shape = shape,
    colors = colors,
    border = border,
)

@Composable
fun DesignSystem.ButtonInline(
    modifier: Modifier = Modifier,
    text: String,
    colors: ButtonColors = buttonPrimaryColors(),
    clickInfo: ClickInfo,
) = Text(
    modifier = modifier
        .clickable(onClick = clickInfo.onClick, enabled = clickInfo.enabled),
    text = text,
    color = colors.contentColor,
)

@Composable
fun buttonPrimaryColors(
    contentColor: Color = DesignSystem.colors.onPrimary,
    containerColor: Color = DesignSystem.colors.primary,
    disabledContentColor: Color = DesignSystem.colors.disabledOnPrimary,
    disabledContainerColor: Color = DesignSystem.colors.disabledPrimary,
) = ButtonColors(
    contentColor = contentColor,
    containerColor = containerColor,
    disabledContentColor = disabledContentColor,
    disabledContainerColor = disabledContainerColor,
)

@Composable
fun buttonSecondaryColors(
    contentColor: Color = DesignSystem.colors.onSecondary,
    containerColor: Color = DesignSystem.colors.secondary,
    disabledContentColor: Color = DesignSystem.colors.disabledOnSecondary,
    disabledContainerColor: Color = DesignSystem.colors.disabledSecondary,
) = ButtonColors(
    contentColor = contentColor,
    containerColor = containerColor,
    disabledContentColor = disabledContentColor,
    disabledContainerColor = disabledContainerColor,
)

@Composable
fun buttonCustomColors(
    contentColor: Color,
    containerColor: Color,
    disabledContentColor: Color,
    disabledContainerColor: Color,
) = ButtonColors(
    contentColor = contentColor,
    containerColor = containerColor,
    disabledContentColor = disabledContentColor,
    disabledContainerColor = disabledContainerColor,
)