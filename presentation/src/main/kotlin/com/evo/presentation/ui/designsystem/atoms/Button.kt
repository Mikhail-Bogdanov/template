package com.evo.presentation.ui.designsystem.atoms

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.evo.presentation.ui.designsystem.theme.ClickInfo
import com.evo.presentation.ui.designsystem.theme.DesignSystem
import com.evo.presentation.ui.designsystem.theme.DesignSystem.Shapes
import androidx.compose.material3.Button as MaterialButton

@Composable
fun DesignSystem.Button(
    modifier: Modifier = Modifier,
    text: CharSequence,
    shape: Shapes = Shapes.Medium,
    colors: ButtonColors = primaryButtonColors(),
    border: BorderStroke? = null,
    softWrap: Boolean = false,
    clickInfo: ClickInfo,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    textStyle: TextStyle = DesignSystem.TextStyles.buttonMedium,
) = MaterialButton(
    modifier = modifier,
    onClick = clickInfo.onClick,
    content = {
        val finalContentColor = if (clickInfo.enabled) {
            colors.contentColor
        } else {
            colors.disabledContentColor
        }
        Text(
            text = text,
            color = finalContentColor,
            style = textStyle,
            softWrap = softWrap,
            textAlign = TextAlign.Center,
        )
    },
    enabled = clickInfo.enabled,
    shape = shape.shape,
    colors = colors,
    border = border,
    contentPadding = contentPadding,
)

@Composable
fun DesignSystem.Button(
    modifier: Modifier = Modifier,
    shape: Shapes = Shapes.Small,
    colors: ButtonColors = primaryButtonColors(),
    border: BorderStroke? = null,
    clickInfo: ClickInfo,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable () -> Unit,
) = MaterialButton(
    modifier = modifier,
    onClick = clickInfo.onClick,
    content = { content() },
    enabled = clickInfo.enabled,
    shape = shape.shape,
    colors = colors,
    border = border,
    contentPadding = contentPadding,
)

@Composable
fun DesignSystem.ButtonInline(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = DesignSystem.TextStyles.title,
    colors: ButtonColors = primaryButtonColors(),
    clickInfo: ClickInfo,
) = Text(
    modifier = modifier.clickable(
        onClick = clickInfo.onClick,
        enabled = clickInfo.enabled,
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
    ),
    text = text,
    style = style,
    color = if (clickInfo.enabled) {
        colors.contentColor
    } else {
        colors.disabledContentColor
    },
)

@Composable
fun DesignSystem.primaryButtonColors(
    contentColor: Color = Colors.content.primary,
    disabledContentColor: Color = Colors.content.disabled,
    containerColor: Color = Colors.container.primary,
    disabledContainerColor: Color = Colors.container.disabled,
) = ButtonColors(
    contentColor = contentColor,
    containerColor = containerColor,
    disabledContentColor = disabledContentColor,
    disabledContainerColor = disabledContainerColor,
)

@Composable
fun DesignSystem.secondaryButtonColors(
    contentColor: Color = Colors.content.primary,
    disabledContentColor: Color = Colors.content.disabled,
    containerColor: Color = Colors.container.secondary,
    disabledContainerColor: Color = Colors.container.disabled,
) = ButtonColors(
    contentColor = contentColor,
    containerColor = containerColor,
    disabledContentColor = disabledContentColor,
    disabledContainerColor = disabledContainerColor,
)

@Composable
fun customButtonColors(
    contentColor: Color,
    disabledContentColor: Color,
    containerColor: Color,
    disabledContainerColor: Color,
) = ButtonColors(
    contentColor = contentColor,
    containerColor = containerColor,
    disabledContentColor = disabledContentColor,
    disabledContainerColor = disabledContainerColor,
)
