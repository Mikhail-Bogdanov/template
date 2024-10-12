package com.evo.designsystem.colors

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.evo.designsystem.theme.ThemeType


internal fun Colors(themeType: ThemeType): Colors = when (themeType) {
    ThemeType.LIGHT -> LightColors
    ThemeType.DARK -> DarkColors
}

val LocalColors: ProvidableCompositionLocal<Colors> = staticCompositionLocalOf { LightColors }

abstract class Colors {

    abstract val background: Color
    abstract val onBackground: Color

    abstract val primary: Color
    abstract val onPrimary: Color

    abstract val secondary: Color
    abstract val onSecondary: Color

    abstract val tertiary: Color
    abstract val onTertiary: Color
    abstract val error: Color

    abstract val onError: Color

}

data object DarkColors : Colors() {

    override val background: Color = Color(0xFF000000)
    override val onBackground: Color = Color(0xFFFFFFFF)

    override val primary: Color = Color(0xFF000000)
    override val onPrimary: Color = Color(0xFFFFFFFF)

    override val secondary: Color = Color(0xFF000000)
    override val onSecondary: Color = Color(0xFFFFFFFF)

    override val tertiary: Color = Color(0xFF000000)
    override val onTertiary: Color = Color(0xFFFFFFFF)

    override val error: Color = Color(0xFF000000)
    override val onError: Color = Color(0xFFFFFFFF)

}

data object LightColors : Colors() {

    override val background: Color = Color(0xFF000000)
    override val onBackground: Color = Color(0xFFFFFFFF)

    override val primary: Color = Color(0xFF000000)
    override val onPrimary: Color = Color(0xFFFFFFFF)

    override val secondary: Color = Color(0xFF000000)
    override val onSecondary: Color = Color(0xFFFFFFFF)

    override val tertiary: Color = Color(0xFF000000)
    override val onTertiary: Color = Color(0xFFFFFFFF)

    override val error: Color = Color(0xFF000000)
    override val onError: Color = Color(0xFFFFFFFF)

}