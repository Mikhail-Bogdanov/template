package com.evo.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider


@Composable
fun MainAppTheme(
    themeType: ThemeType,
    content: @Composable () -> Unit
) = CompositionLocalProvider(
    LocalColors provides Colors(themeType),
    content = content
)

object DesignSystem {
    val colors: Colors
        @Composable
        get() = LocalColors.current

    val textStyles: TextStyles = TextStyles

    val shapes: Shapes = Shapes
}

enum class ThemeType {
    LIGHT, DARK,
}