package com.evo.presentation.ui.designsystem.theme

import android.content.Context
import android.os.LocaleList
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
import com.evo.resources.system.R
import com.evo.storage.*
import org.koin.compose.koinInject

@Composable
fun MainAppTheme(content: @Composable BoxScope.() -> Unit) {
    val localeHandler = koinInject<LocaleHandler>()
    val themeHandler = koinInject<ThemeHandler>()

    val locale by localeHandler.localeFlow.collectAsStateWithLifecycle(EvoLocale.DEFAULT)
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
    config.setLocales(LocaleList.forLanguageTags(locale.languageTag))
    return createConfigurationContext(config)
}

val LocalLocale = staticCompositionLocalOf { EvoLocale.DEFAULT }
val LocalTheme = staticCompositionLocalOf { EvoTheme.DEFAULT }

object DesignSystem {

    val Colors: DSColors
        @Composable get() = LocalColors.current

    @Immutable
    sealed class Shapes(val shape: RoundedCornerShape) {

        @Immutable
        sealed class Element(size: Dp) : Shapes(RoundedCornerShape(size)) {

            data object None : Element(0.dp)
            data object ExtraSmall : Element(4.dp)
            data object Small : Element(8.dp)
            data object Medium : Element(12.dp)
            data object Big : Element(16.dp)
            data object Large : Element(20.dp)
            data object ExtraLarge : Element(24.dp)
            data object Full : Element(100.dp)

        }

        @Immutable
        sealed class Bar(shape: RoundedCornerShape) : Shapes(shape) {

            @Immutable
            sealed class Top(size: Dp) : Bar(
                RoundedCornerShape(
                    topStart = CornerSize(0),
                    topEnd = CornerSize(0),
                    bottomEnd = CornerSize(size),
                    bottomStart = CornerSize(size),
                )
            ) {

                data object None : Top(0.dp)
                data object Small : Top(8.dp)
                data object Big : Top(16.dp)
            }

            @Immutable
            sealed class Bottom(shape: RoundedCornerShape) : Bar(shape) {

                sealed class Floating(size: Dp) : Bottom(RoundedCornerShape(size)) {

                    data object None : Floating(0.dp)
                    data object Small : Floating(8.dp)
                    data object Big : Floating(16.dp)
                }

                sealed class Static(size: Dp) : Bottom(
                    RoundedCornerShape(
                        topStart = CornerSize(size),
                        topEnd = CornerSize(size),
                        bottomEnd = CornerSize(0),
                        bottomStart = CornerSize(0),
                    )
                ) {

                    data object None : Static(0.dp)
                    data object Small : Static(8.dp)
                    data object Big : Static(16.dp)
                }
            }
        }

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

        // xD
        val DSPx0 = DesignSystemPadding * 0f
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
