package com.evo.designsystem.atoms

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.evo.designsystem.theme.DesignSystem
import com.evo.designsystem.theme.EmptyColor
import androidx.compose.material3.Checkbox as MaterialCheckbox
import androidx.compose.material3.CheckboxColors as MaterialCheckboxColors

@Composable
fun DesignSystem.Checkbox(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    enabled: Boolean = true,
    colors: CheckboxColors = primaryCheckboxColors(),
) = MaterialCheckbox(
    modifier = modifier,
    checked = checked,
    onCheckedChange = onCheckedChange,
    enabled = enabled,
    colors = colors.toMaterialCheckboxColors()
)

@Composable
fun primaryCheckboxColors(
    checkedCheckmarkColor: Color = DesignSystem.colors.primary,
    checkedBorderColor: Color = DesignSystem.colors.primary,
    disabledBorderColor: Color = DesignSystem.colors.disabledPrimary,
) = CheckboxColors(
    checkedCheckmarkColor = checkedCheckmarkColor,
    checkedBorderColor = checkedBorderColor,
    disabledBorderColor = disabledBorderColor,
)

@Composable
fun secondaryCheckboxColors(
    checkedCheckmarkColor: Color = DesignSystem.colors.secondary,
    checkedBorderColor: Color = DesignSystem.colors.secondary,
    disabledBorderColor: Color = DesignSystem.colors.disabledSecondary,
) = CheckboxColors(
    checkedCheckmarkColor = checkedCheckmarkColor,
    checkedBorderColor = checkedBorderColor,
    disabledBorderColor = disabledBorderColor,
)

@Immutable
data class CheckboxColors(
    val checkedCheckmarkColor: Color,
    val checkedBorderColor: Color,
    val disabledBorderColor: Color,
)

private fun CheckboxColors.toMaterialCheckboxColors() = MaterialCheckboxColors(
    checkedCheckmarkColor = checkedCheckmarkColor,
    uncheckedCheckmarkColor = EmptyColor,
    checkedBoxColor = EmptyColor,
    uncheckedBoxColor = EmptyColor,
    disabledCheckedBoxColor = EmptyColor,
    disabledUncheckedBoxColor = EmptyColor,
    disabledIndeterminateBoxColor = EmptyColor,
    checkedBorderColor = checkedBorderColor,
    uncheckedBorderColor = checkedBorderColor,
    disabledBorderColor = disabledBorderColor,
    disabledUncheckedBorderColor = disabledBorderColor,
    disabledIndeterminateBorderColor = EmptyColor,
)