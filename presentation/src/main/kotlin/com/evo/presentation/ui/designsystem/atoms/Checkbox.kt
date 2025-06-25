package com.evo.presentation.ui.designsystem.atoms

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.evo.presentation.ui.designsystem.theme.DesignSystem
import com.evo.presentation.ui.designsystem.theme.EmptyColor
import androidx.compose.material3.Checkbox as MaterialCheckbox
import androidx.compose.material3.CheckboxColors as MaterialCheckboxColors

@Composable
fun DesignSystem.Checkbox(
    modifier: Modifier = Modifier,
    checked: Boolean,
    colors: CheckboxColors = primaryCheckboxColors(),
    onCheckedChange: ((Boolean) -> Unit)? = null,
    enabled: Boolean = true,
) = MaterialCheckbox(
    modifier = modifier,
    checked = checked,
    onCheckedChange = onCheckedChange,
    enabled = enabled,
    colors = colors.toMaterialCheckboxColors(),
)

@Composable
fun DesignSystem.primaryCheckboxColors(
    checkedCheckmarkColor: Color = Colors.content.primary,
    checkedBorderColor: Color = Colors.container.primary,
    disabledBorderColor: Color = Colors.container.disabled,
) = CheckboxColors(
    checkedCheckmarkColor,
    checkedBorderColor,
    disabledBorderColor,
)

@Composable
fun DesignSystem.customColors(
    checkedCheckmarkColor: Color,
    checkedBorderColor: Color,
    disabledBorderColor: Color,
) = CheckboxColors(
    checkedCheckmarkColor,
    checkedBorderColor,
    disabledBorderColor,
)

@Immutable
data class CheckboxColors(
    val checkedCheckmarkColor: Color,
    val checkedBorderColor: Color,
    val disabledBorderColor: Color,
)

private fun CheckboxColors.toMaterialCheckboxColors() = MaterialCheckboxColors(
    checkedCheckmarkColor = checkedCheckmarkColor,
    uncheckedCheckmarkColor = DesignSystem.EmptyColor,
    checkedBoxColor = DesignSystem.EmptyColor,
    uncheckedBoxColor = DesignSystem.EmptyColor,
    disabledCheckedBoxColor = DesignSystem.EmptyColor,
    disabledUncheckedBoxColor = DesignSystem.EmptyColor,
    disabledIndeterminateBoxColor = DesignSystem.EmptyColor,
    checkedBorderColor = checkedBorderColor,
    uncheckedBorderColor = checkedBorderColor,
    disabledBorderColor = disabledBorderColor,
    disabledUncheckedBorderColor = disabledBorderColor,
    disabledIndeterminateBorderColor = DesignSystem.EmptyColor,
)
