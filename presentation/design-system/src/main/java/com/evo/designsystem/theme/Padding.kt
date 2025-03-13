package com.evo.designsystem.theme

import androidx.compose.ui.unit.Dp

object Padding {

    private val DesignSystemPadding = Dp(4f)

    val DSPx1 = DesignSystemPadding * 1f
    val DSPx1_5 = DesignSystemPadding * 1.5f
    val DSPx2 = DesignSystemPadding * 2f
    val DSPx2_5 = DesignSystemPadding * 2.5f
    val DSPx3 = DesignSystemPadding * 3f
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

    fun custom(multiplier: Float) = DesignSystemPadding * multiplier
}