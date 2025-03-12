package com.evo.designsystem.atoms

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.evo.designsystem.theme.DesignSystem
import com.evo.designsystem.theme.EmptyColor
import androidx.compose.material3.Switch as MaterialSwitch
import androidx.compose.material3.SwitchColors as MaterialSwitchColors

@Composable
fun DesignSystem.Switch(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)? = null,
    enabled: Boolean = true,
    colors: SwitchColors = primarySwitchColors(),
) = MaterialSwitch(
    modifier = modifier,
    checked = checked,
    onCheckedChange = onCheckedChange,
    enabled = enabled,
    colors = colors.toMaterialSwitchColors()
)

@Composable
fun primarySwitchColors(
    checkedThumbColor: Color = DesignSystem.colors.onPrimary,
    checkedTrackColor: Color = DesignSystem.colors.primary,
    checkedBorderColor: Color = DesignSystem.colors.onPrimary,
    uncheckedThumbColor: Color = DesignSystem.colors.alternativePrimary,
    uncheckedTrackColor: Color = DesignSystem.colors.primary,
    uncheckedBorderColor: Color = DesignSystem.colors.alternativePrimary,
    disabledThumbColor: Color = DesignSystem.colors.disabledOnPrimary,
    disabledTrackColor: Color = DesignSystem.colors.disabledPrimary,
    disabledBorderColor: Color = DesignSystem.colors.disabledOnPrimary,
) = SwitchColors(
    checkedThumbColor = checkedThumbColor,
    checkedTrackColor = checkedTrackColor,
    checkedBorderColor = checkedBorderColor,
    uncheckedThumbColor = uncheckedThumbColor,
    uncheckedTrackColor = uncheckedTrackColor,
    uncheckedBorderColor = uncheckedBorderColor,
    disabledThumbColor = disabledThumbColor,
    disabledTrackColor = disabledTrackColor,
    disabledBorderColor = disabledBorderColor,
)

@Composable
fun secondarySwitchColors(
    checkedThumbColor: Color = DesignSystem.colors.onSecondary,
    checkedTrackColor: Color = DesignSystem.colors.secondary,
    checkedBorderColor: Color = DesignSystem.colors.onSecondary,
    uncheckedThumbColor: Color = DesignSystem.colors.alternativeSecondary,
    uncheckedTrackColor: Color = DesignSystem.colors.secondary,
    uncheckedBorderColor: Color = DesignSystem.colors.alternativeSecondary,
    disabledThumbColor: Color = DesignSystem.colors.disabledOnSecondary,
    disabledTrackColor: Color = DesignSystem.colors.disabledSecondary,
    disabledBorderColor: Color = DesignSystem.colors.disabledOnSecondary,
) = SwitchColors(
    checkedThumbColor = checkedThumbColor,
    checkedTrackColor = checkedTrackColor,
    checkedBorderColor = checkedBorderColor,
    uncheckedThumbColor = uncheckedThumbColor,
    uncheckedTrackColor = uncheckedTrackColor,
    uncheckedBorderColor = uncheckedBorderColor,
    disabledThumbColor = disabledThumbColor,
    disabledTrackColor = disabledTrackColor,
    disabledBorderColor = disabledBorderColor,
)

@Immutable
data class SwitchColors(
    val checkedThumbColor: Color,
    val checkedTrackColor: Color,
    val checkedBorderColor: Color,
    val uncheckedThumbColor: Color,
    val uncheckedTrackColor: Color,
    val uncheckedBorderColor: Color,
    val disabledThumbColor: Color,
    val disabledTrackColor: Color,
    val disabledBorderColor: Color,
)

private fun SwitchColors.toMaterialSwitchColors() = MaterialSwitchColors(
    checkedThumbColor = checkedThumbColor,
    checkedTrackColor = checkedTrackColor,
    checkedBorderColor = checkedBorderColor,
    checkedIconColor = EmptyColor,
    uncheckedThumbColor = uncheckedThumbColor,
    uncheckedTrackColor = uncheckedTrackColor,
    uncheckedBorderColor = uncheckedBorderColor,
    uncheckedIconColor = EmptyColor,
    disabledCheckedThumbColor = disabledThumbColor,
    disabledCheckedTrackColor = disabledTrackColor,
    disabledCheckedBorderColor = disabledBorderColor,
    disabledCheckedIconColor = EmptyColor,
    disabledUncheckedThumbColor = disabledThumbColor,
    disabledUncheckedTrackColor = disabledTrackColor,
    disabledUncheckedBorderColor = disabledBorderColor,
    disabledUncheckedIconColor = EmptyColor,
)