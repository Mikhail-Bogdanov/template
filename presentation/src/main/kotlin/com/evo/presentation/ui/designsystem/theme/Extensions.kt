package com.evo.presentation.ui.designsystem.theme

import androidx.compose.foundation.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*

fun Modifier.clip(shape: DesignSystem.Shapes) = clip(shape.shape)

fun Modifier.background(
    color: Color,
    shape: DesignSystem.Shapes = DesignSystem.Shapes.None,
) = background(color, shape.shape)

fun Modifier.border(
    border: BorderStroke,
    shape: DesignSystem.Shapes = DesignSystem.Shapes.None,
) = border(border = border, shape = shape.shape)

inline fun AnnotatedString.Builder.asTitle(block: AnnotatedString.Builder.() -> Unit) {
    withStyle(DesignSystem.TextStyles.title.toSpanStyle(), block)
}

inline fun AnnotatedString.Builder.withStyle(
    style: TextStyle,
    block: AnnotatedString.Builder.() -> Unit,
) = withStyle(style.toSpanStyle(), block)
