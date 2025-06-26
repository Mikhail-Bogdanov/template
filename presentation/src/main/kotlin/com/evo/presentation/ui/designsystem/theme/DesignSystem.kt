package com.evo.presentation.ui.designsystem.theme

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.evo.resources.LocalLocale
import com.evo.resources.R
import com.evo.storage.*
import org.koin.compose.koinInject
import java.util.Locale

@Composable
fun MainAppTheme(content: @Composable BoxScope.() -> Unit) {
    val localeHandler = koinInject<LocaleHandler>()
    val themeHandler = koinInject<ThemeHandler>()

    val locale by localeHandler.localeFlow.collectAsStateWithLifecycle(EvoLocale.Default)
    val theme by themeHandler.themeFlow.collectAsStateWithLifecycle(EvoTheme.DEFAULT)

    val newContext = LocalContext.current.withLocale(locale)

    val isDarkTheme = if (theme.isDefault) isSystemInDarkTheme() else theme == EvoTheme.DARK

    CompositionLocalProvider(
        LocalColors provides DSColors(isDarkTheme),
        LocalLocale provides locale,
        LocalTheme provides theme,
        LocalContext provides newContext
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DesignSystem.Colors.background.level0)
                .navigationBarsPadding(),
            contentAlignment = Alignment.Center,
            content = content,
        )
    }
}

private fun Context.withLocale(locale: EvoLocale): Context {
    val config = resources.configuration
    config.setLocale(Locale.forLanguageTag(locale.languageTag))
    return createConfigurationContext(config)
}

val LocalTheme = staticCompositionLocalOf { EvoTheme.DEFAULT }

object DesignSystem {

    val Colors: DSColors
        @Composable get() = LocalColors.current

    @Immutable
    sealed class Shapes(val shape: RoundedCornerShape) {

        data object None : Shapes(RoundedCornerShape(0.dp))
        data object ExtraSmall : Shapes(RoundedCornerShape(4.dp))
        data object Small : Shapes(RoundedCornerShape(8.dp))
        data object Medium : Shapes(RoundedCornerShape(12.dp))
        data object Big : Shapes(RoundedCornerShape(16.dp))
        data object Large : Shapes(RoundedCornerShape(20.dp))
        data object ExtraLarge : Shapes(RoundedCornerShape(24.dp))
        data object Full : Shapes(RoundedCornerShape(100.dp))

        fun Shapes.asTopBar(floating: Boolean = false): RoundedCornerShape = RoundedCornerShape(
            topStart = shape.topStart.takeIf { floating } ?: CornerSize(0),
            topEnd = shape.topEnd.takeIf { floating } ?: CornerSize(0),
            bottomEnd = shape.bottomEnd,
            bottomStart = shape.bottomStart,
        )

        fun Shapes.asBottomBar(floating: Boolean = false): RoundedCornerShape = RoundedCornerShape(
            topStart = shape.topStart,
            topEnd = shape.topEnd,
            bottomEnd = shape.bottomEnd.takeIf { floating } ?: CornerSize(0),
            bottomStart = shape.bottomStart.takeIf { floating } ?: CornerSize(0),
        )

        companion object {

            fun custom(size: Dp) = RoundedCornerShape(size)

            fun custom(
                topStart: Dp = 0.dp,
                topEnd: Dp = 0.dp,
                bottomEnd: Dp = 0.dp,
                bottomStart: Dp = 0.dp,
            ) = RoundedCornerShape(
                topStart = CornerSize(topStart),
                topEnd = CornerSize(topEnd),
                bottomEnd = CornerSize(bottomEnd),
                bottomStart = CornerSize(bottomStart)
            )
        }
    }

    object Paddings {

        private val DesignSystemPadding = Dp(4f)

        val DSPx0_5 = DesignSystemPadding * 0.5f
        val DSPx1 = DesignSystemPadding * 1f
        val DSPx1_5 = DesignSystemPadding * 1.5f
        val DSPx2 = DesignSystemPadding * 2f
        val DSPx2_5 = DesignSystemPadding * 2.5f
        val DSPx3 = DesignSystemPadding * 3f
        val DSPx3_5 = DesignSystemPadding * 3.5f
        val DSPx4 = DesignSystemPadding * 4f
        val DSPx5 = DesignSystemPadding * 5f
        val DSPx6 = DesignSystemPadding * 6f
        val DSPx7 = DesignSystemPadding * 7f
        val DSPx8 = DesignSystemPadding * 8f
        val DSPx9 = DesignSystemPadding * 9f
        val DSPx10 = DesignSystemPadding * 10f
        val DSPx11 = DesignSystemPadding * 11f
        val DSPx12 = DesignSystemPadding * 12f
        val DSPx13 = DesignSystemPadding * 13f
        val DSPx14 = DesignSystemPadding * 14f
        val DSPx15 = DesignSystemPadding * 15f
        val DSPx20 = DesignSystemPadding * 20f
        val DSPx25 = DesignSystemPadding * 25f
        val DSPx30 = DesignSystemPadding * 30f

        fun custom(multiplier: Float) = DesignSystemPadding * multiplier
    }

    object TextStyles {

        private fun getFontFamily(weight: FontWeight) = FontFamily(Font(R.font.rubik, weight))

        val headline = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 28.sp,
            fontFamily = getFontFamily(FontWeight.Medium),
        )

        val title = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 22.sp,
            fontFamily = getFontFamily(FontWeight.Medium),
        )

        val body = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 18.sp,
            fontFamily = getFontFamily(FontWeight.Normal),
        )

        val description = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            lineHeight = 14.sp,
            fontFamily = getFontFamily(FontWeight.Light),
        )

        val buttonBig = TextStyle(
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 24.sp,
            fontFamily = getFontFamily(FontWeight.Medium),
        )

        val buttonMedium = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 20.sp,
            fontFamily = getFontFamily(FontWeight.Normal),
        )

        val buttonSmall = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            lineHeight = 16.sp,
            fontFamily = getFontFamily(FontWeight.Light),
        )
    }
}
