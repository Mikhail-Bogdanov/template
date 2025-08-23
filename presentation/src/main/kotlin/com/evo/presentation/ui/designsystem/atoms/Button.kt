package com.evo.presentation.ui.designsystem.atoms

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.evo.presentation.ui.conditional
import com.evo.presentation.ui.designsystem.theme.*
import com.evo.presentation.ui.designsystem.theme.DesignSystem.Shapes

@Composable
fun DesignSystem.Button(
    modifier: Modifier = Modifier,
    text: CharSequence,
    shape: Shapes = Shapes.Medium,
    colors: ButtonColors = primaryButtonColors(),
    softWrap: Boolean = false,
    clickInfo: ClickInfo,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    style: TextStyle = DesignSystem.TextStyles.buttonMedium,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) = Text(
    text = text,
    modifier = modifier
        .clip(shape)
        .clickable(
            enabled = clickInfo.enabled,
            onClick = clickInfo.onClick,
            indication = ripple(
                bounded = false,
                color = colors.contentColor,
            ),
            interactionSource = interactionSource,
        )
        .conditional(
            condition = clickInfo.enabled,
            operation = {
                background(colors.containerColor, shape)
            },
            elseOperation = {
                background(colors.disabledContainerColor, shape)
            },
        )
        .padding(contentPadding),
    softWrap = softWrap,
    style = style,
    textAlign = TextAlign.Center,
    color = if (clickInfo.enabled) colors.contentColor else colors.disabledContentColor,
)

@Composable
fun DesignSystem.Button(
    modifier: Modifier = Modifier,
    shape: Shapes = Shapes.Small,
    colors: ButtonColors = primaryButtonColors(),
    clickInfo: ClickInfo,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit,
) = Box(
    modifier = modifier
        .clip(shape)
        .clickable(
            enabled = clickInfo.enabled,
            onClick = clickInfo.onClick,
            indication = ripple(
                bounded = false,
                color = colors.contentColor,
            ),
            interactionSource = interactionSource,
        )
        .conditional(
            condition = clickInfo.enabled,
            operation = {
                background(colors.containerColor, shape)
            },
            elseOperation = {
                background(colors.disabledContainerColor, shape)
            },
        )
        .padding(contentPadding)
) { content() }

@Composable
fun DesignSystem.ButtonOutline(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = DesignSystem.TextStyles.buttonMedium,
    colors: ButtonColors = primaryButtonColors(),
    clickInfo: ClickInfo,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    softWrap: Boolean = false,
    shape: Shapes = Shapes.Medium,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
) = Text(
    modifier = modifier
        .clip(shape)
        .clickable(
            enabled = clickInfo.enabled,
            onClick = clickInfo.onClick,
            indication = ripple(
                bounded = false,
                color = colors.contentColor,
            ),
            interactionSource = interactionSource,
        )
        .border(
            color = if (clickInfo.enabled) colors.containerColor else colors.disabledContainerColor,
            width = DesignSystem.Paddings.DSPx0_5,
            shape = shape.shape,
        )
        .padding(contentPadding),
    text = text,
    style = style,
    color = if (clickInfo.enabled) colors.contentColor else colors.disabledContentColor,
    softWrap = softWrap,
)

@Composable
fun DesignSystem.ButtonInline(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = DesignSystem.TextStyles.buttonMedium,
    colors: ButtonColors = primaryButtonColors(),
    clickInfo: ClickInfo,
    softWrap: Boolean = false,
    shape: Shapes = Shapes.Medium,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) = Text(
    modifier = modifier
        .clip(shape)
        .clickable(
            enabled = clickInfo.enabled,
            onClick = clickInfo.onClick,
            indication = ripple(
                bounded = false,
                color = colors.contentColor,
            ),
            interactionSource = interactionSource,
        )
        .padding(contentPadding),
    text = text,
    style = style,
    color = if (clickInfo.enabled) colors.contentColor else colors.disabledContentColor,
    softWrap = softWrap,
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
