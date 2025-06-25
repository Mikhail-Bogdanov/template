package com.evo.presentation.ui.designsystem.atoms

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.evo.presentation.ui.designsystem.theme.DesignSystem
import com.evo.presentation.ui.designsystem.theme.EmptyColor
import com.evo.presentation.ui.designsystem.theme.alphaDecreased
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
fun DesignSystem.primarySwitchColors(
    checkedContentColor: Color = Colors.content.primary,
    uncheckedContentColor: Color = Colors.content.primary.alphaDecreased(0.5f),
    contentColorDisabled: Color = Colors.content.disabled,
    trackColor: Color = Colors.container.primary,
    trackColorDisabled: Color = Colors.container.disabled,
) = SwitchColors(
    checkedContentColor = checkedContentColor,
    uncheckedContentColor = uncheckedContentColor,
    contentColorDisabled = contentColorDisabled,
    trackColor = trackColor,
    trackColorDisabled = trackColorDisabled,
)

@Composable
fun DesignSystem.secondarySwitchColors(
    checkedContentColor: Color = Colors.content.primary,
    uncheckedContentColor: Color = Colors.content.primary.alphaDecreased(0.5f),
    contentColorDisabled: Color = Colors.content.disabled,
    trackColor: Color = Colors.container.secondary,
    trackColorDisabled: Color = Colors.container.disabled,
) = SwitchColors(
    checkedContentColor = checkedContentColor,
    uncheckedContentColor = uncheckedContentColor,
    contentColorDisabled = contentColorDisabled,
    trackColor = trackColor,
    trackColorDisabled = trackColorDisabled,
)

@Immutable
data class SwitchColors(
    val checkedContentColor: Color,
    val uncheckedContentColor: Color,
    val contentColorDisabled: Color,
    val trackColor: Color,
    val trackColorDisabled: Color,
)

@Composable
private fun SwitchColors.toMaterialSwitchColors() = MaterialSwitchColors(
    checkedThumbColor = checkedContentColor,
    checkedTrackColor = trackColor,
    checkedBorderColor = checkedContentColor,
    uncheckedThumbColor = uncheckedContentColor,
    uncheckedTrackColor = trackColor,
    uncheckedBorderColor = uncheckedContentColor,
    disabledCheckedThumbColor = contentColorDisabled,
    disabledCheckedTrackColor = trackColorDisabled,
    disabledCheckedBorderColor = contentColorDisabled,
    disabledUncheckedThumbColor = contentColorDisabled,
    disabledUncheckedTrackColor = trackColorDisabled,
    disabledUncheckedBorderColor = contentColorDisabled,
    disabledUncheckedIconColor = DesignSystem.EmptyColor,
    disabledCheckedIconColor = DesignSystem.EmptyColor,
    uncheckedIconColor = DesignSystem.EmptyColor,
    checkedIconColor = DesignSystem.EmptyColor,
)
