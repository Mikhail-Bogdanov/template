package com.evo.presentation.ui.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.evo.presentation.ui.designsystem.theme.DSColors.*

internal fun DSColors(isDark: Boolean) = if (isDark) DSColorsDark else DSColorsLight

val LocalColors = staticCompositionLocalOf<DSColors> { DSColorsLight }

@Immutable
interface DSColors {

    val content: DSContent
    val container: DSContainer
    val background: DSSurface

    @Immutable
    interface DSContent {

        val primary: Color
        val secondary: Color
        val highlight: Color
        val disabled: Color
        val danger: Color
    }

    @Immutable
    interface DSContainer {

        val primary: Color
        val secondary: Color
        val disabled: Color
        val danger: Color
    }

    /**
     * higher level - lighter color
     */
    @Immutable
    interface DSSurface {

        val level0: Color
        val level1: Color
        val level2: Color

        val dialog: Color
    }
}

@Immutable
data object DSColorsLight : DSColors {

    override val content: DSContent = Content
    override val container: DSContainer = Container
    override val background: DSSurface = Background

    @Immutable
    private data object Content : DSContent {

        override val primary: Color = Color(0xFF441F9F)
        override val secondary: Color = Color(0xFF256ABE)
        override val highlight: Color = Color(0xFF772AD2)
        override val disabled: Color = Color(0xFF363344)
        override val danger: Color = Color(0xFF570B11)
    }

    @Immutable
    private data object Container : DSContainer {

        override val primary: Color = Color(0xFFCFC6E3)
        override val secondary: Color = Color(0xFFACC6E3)
        override val disabled: Color = Color(0xFFC2C0C4)
        override val danger: Color = Color(0xFFFFD1D7)
    }

    @Immutable
    private data object Background : DSSurface {

        override val level0: Color = Color(0xFFF3F2F8)
        override val level1: Color = Color(0xFFEBE7F5)
        override val level2: Color = Color(0xFFDED6EC)

        override val dialog: Color = Color(0xFFD3C8EE)
    }
}

@Immutable
data object DSColorsDark : DSColors {

    override val content: DSContent = Content
    override val container: DSContainer = Container
    override val background: DSSurface = Background

    @Immutable
    private data object Content : DSContent {

        override val primary: Color = Color(0xFFE4D2FF)
        override val secondary: Color = Color(0xFFBAD2F3)
        override val highlight: Color = Color(0xFFCFA9FA)
        override val disabled: Color = Color(0xFFBCB8C2)
        override val danger: Color = Color(0xFFFFBCC1)
    }

    @Immutable
    private data object Container : DSContainer {

        override val primary: Color = Color(0xFF453A57)
        override val secondary: Color = Color(0xFF2D3656)
        override val disabled: Color = Color(0xFF33303A)
        override val danger: Color = Color(0xFF5B353F)
    }

    @Immutable
    private data object Background : DSSurface {

        override val level0: Color = Color(0xFF30243B)
        override val level1: Color = Color(0xFF352941)
        override val level2: Color = Color(0xFF352B42)

        override val dialog: Color = Color(0xFF4E4065)
    }
}

val DesignSystem.EmptyColor get() = Color.Transparent

fun Color.alphaDecreased(alphaValue: Float = 0.75f) = copy(alpha = alphaValue)
