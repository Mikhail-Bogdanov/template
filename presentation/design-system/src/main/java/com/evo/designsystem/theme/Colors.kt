package com.evo.designsystem.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.evo.storage.theme.EvoTheme


internal fun Colors(themeType: EvoTheme): Colors = when (themeType) {
    EvoTheme.LIGHT -> LightColors
    EvoTheme.DARK -> DarkColors
}

val LocalColors = staticCompositionLocalOf<Colors> { LightColors }

abstract class Colors {

    abstract val background: Color
    abstract val onBackground: Color

    abstract val primary: Color
    abstract val alternativePrimary: Color
    abstract val errorPrimary: Color
    abstract val disabledPrimary: Color
    abstract val onPrimary: Color
    abstract val disabledOnPrimary: Color

    abstract val secondary: Color
    abstract val alternativeSecondary: Color
    abstract val errorSecondary: Color
    abstract val disabledSecondary: Color
    abstract val onSecondary: Color
    abstract val disabledOnSecondary: Color

    abstract val tertiary: Color
    abstract val onTertiary: Color

    abstract val link: Color

    abstract val error: Color
    abstract val onError: Color

    abstract val success: Color
    abstract val onSuccess: Color

}

data object DarkColors : Colors() {


    override val background: Color = Color(0xFF000000)
    override val onBackground: Color = Color(0xFFFFFFFF)

    override val primary: Color = Color(0xFF000000)
    override val alternativePrimary: Color = Color(0xFF000000)
    override val errorPrimary: Color = Color(0xFFFFFFFF)
    override val disabledPrimary: Color = Color(0xFF000000)
    override val disabledOnPrimary: Color = Color(0xFFFFFFFF)
    override val onPrimary: Color = Color(0xFFFFFFFF)

    override val secondary: Color = Color(0xFF000000)
    override val alternativeSecondary: Color = Color(0xFF000000)
    override val errorSecondary: Color = Color(0xFFFFFFFF)
    override val disabledSecondary: Color = Color(0xFF000000)
    override val disabledOnSecondary: Color = Color(0xFFFFFFFF)
    override val onSecondary: Color = Color(0xFFFFFFFF)

    override val tertiary: Color = Color(0xFF000000)
    override val onTertiary: Color = Color(0xFFFFFFFF)

    override val link: Color = Color(0xFF000000)

    override val error: Color = Color(0xFF000000)
    override val onError: Color = Color(0xFFFFFFFF)

    override val success: Color = Color(0xFF000000)
    override val onSuccess: Color = Color(0xFF000000)

}

data object LightColors : Colors() {

    override val background: Color = Color(0xFF000000)
    override val onBackground: Color = Color(0xFFFFFFFF)

    override val primary: Color = Color(0xFF000000)
    override val alternativePrimary: Color = Color(0xFF000000)
    override val errorPrimary: Color = Color(0xFFFFFFFF)
    override val disabledPrimary: Color = Color(0xFF000000)
    override val disabledOnPrimary: Color = Color(0xFFFFFFFF)
    override val onPrimary: Color = Color(0xFFFFFFFF)

    override val secondary: Color = Color(0xFF000000)
    override val alternativeSecondary: Color = Color(0xFF000000)
    override val errorSecondary: Color = Color(0xFFFFFFFF)
    override val disabledSecondary: Color = Color(0xFF000000)
    override val disabledOnSecondary: Color = Color(0xFFFFFFFF)
    override val onSecondary: Color = Color(0xFFFFFFFF)

    override val tertiary: Color = Color(0xFF000000)
    override val onTertiary: Color = Color(0xFFFFFFFF)

    override val link: Color = Color(0xFF000000)

    override val error: Color = Color(0xFF000000)
    override val onError: Color = Color(0xFFFFFFFF)

    override val success: Color = Color(0xFF000000)
    override val onSuccess: Color = Color(0xFF000000)

}

val EmptyColor = Color.Transparent

fun Color.alphaDecreased(alphaValue: Float = 0.75f) = copy(alpha = alphaValue)