package com.evo.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.evo.designsystem.colors.Colors
import com.evo.designsystem.colors.LocalColors
import com.evo.designsystem.shapes.Shapes
import com.evo.designsystem.textstyles.TextStyles


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