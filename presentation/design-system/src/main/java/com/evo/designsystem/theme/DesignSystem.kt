package com.evo.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.evo.storage.theme.EvoTheme


@Composable
fun MainAppTheme(
    theme: EvoTheme,
    content: @Composable () -> Unit
) = CompositionLocalProvider(
    LocalColors provides Colors(theme),
    content = content
)

object DesignSystem {
    val colors: Colors
        @Composable
        get() = LocalColors.current

    val textStyles: TextStyles = TextStyles

    val shapes: Shapes = Shapes
}
