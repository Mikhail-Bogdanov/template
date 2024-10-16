package com.evo.designsystem.atoms

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.evo.designsystem.theme.DesignSystem
import com.evo.extensions.Lambda

@Composable
fun DesignSystem.Button(
    modifier: Modifier = Modifier,
    onClick: Lambda,
    content: @Composable RowScope.() -> Unit,
    enabled: Boolean = true,
    shape: Shape = shapes.medium,
    colors: ButtonColors = buttonPrimaryColors(),
    border: BorderStroke? = null,
) = androidx.compose.material3.Button(
    modifier = modifier,
    onClick = onClick,
    content = content,
    enabled = enabled,
    shape = shape,
    colors = colors,
    border = border,
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

