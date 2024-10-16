package com.evo.designsystem.atoms

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import com.evo.designsystem.theme.DesignSystem
import com.evo.designsystem.theme.EmptyColor
import com.evo.designsystem.theme.alphaDecreased

@Composable
fun DesignSystem.InputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    style: TextStyle = textStyles.body,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    shape: Shape = shapes.medium,
    colors: TextFieldColors = primaryInputFieldColors(),
) = TextField(
    modifier = modifier,
    value = value,
    onValueChange = onValueChange,
    enabled = enabled,
    textStyle = style,
    placeholder = placeholder,
    leadingIcon = leadingIcon,
    trailingIcon = trailingIcon,
    supportingText = supportingText,
    isError = isError,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
    singleLine = singleLine,
    maxLines = maxLines,
    minLines = minLines,
    shape = shape,
    colors = colors,
)

@Composable
fun primaryInputFieldColors(
    focusedTextColor: Color = DesignSystem.colors.onPrimary,
    unfocusedTextColor: Color = DesignSystem.colors.onPrimary,
    disabledTextColor: Color = DesignSystem.colors.disabledOnPrimary,
    errorTextColor: Color = DesignSystem.colors.errorPrimary,

    focusedContainerColor: Color = DesignSystem.colors.primary,
    unfocusedContainerColor: Color = DesignSystem.colors.primary,
    disabledContainerColor: Color = DesignSystem.colors.disabledPrimary,
    errorContainerColor: Color = DesignSystem.colors.primary,

    cursorColor: Color = DesignSystem.colors.primary,
    errorCursorColor: Color = DesignSystem.colors.errorPrimary,

    textSelectionColors: TextSelectionColors = LocalTextSelectionColors.current,

    focusedLeadingIconColor: Color = DesignSystem.colors.onPrimary,
    unfocusedLeadingIconColor: Color = DesignSystem.colors.onPrimary,
    disabledLeadingIconColor: Color = DesignSystem.colors.disabledOnPrimary,
    errorLeadingIconColor: Color = DesignSystem.colors.errorPrimary,

    focusedTrailingIconColor: Color = DesignSystem.colors.onPrimary,
    unfocusedTrailingIconColor: Color = DesignSystem.colors.onPrimary,
    disabledTrailingIconColor: Color = DesignSystem.colors.disabledOnPrimary,
    errorTrailingIconColor: Color = DesignSystem.colors.errorPrimary,

    focusedPlaceholderColor: Color = DesignSystem.colors.onPrimary,
    unfocusedPlaceholderColor: Color = DesignSystem.colors.onPrimary,
    disabledPlaceholderColor: Color = DesignSystem.colors.disabledOnPrimary,
    errorPlaceholderColor: Color = DesignSystem.colors.errorPrimary,

    focusedSupportingTextColor: Color = DesignSystem.colors.onPrimary,
    unfocusedSupportingTextColor: Color = DesignSystem.colors.onPrimary,
    disabledSupportingTextColor: Color = DesignSystem.colors.disabledOnPrimary,
    errorSupportingTextColor: Color = DesignSystem.colors.errorPrimary,
) = TextFieldColors(
    focusedTextColor = focusedTextColor,
    unfocusedTextColor = unfocusedTextColor.alphaDecreased(),
    disabledTextColor = disabledTextColor,
    errorTextColor = errorTextColor,

    focusedContainerColor = focusedContainerColor,
    unfocusedContainerColor = unfocusedContainerColor.alphaDecreased(),
    disabledContainerColor = disabledContainerColor,
    errorContainerColor = errorContainerColor,

    cursorColor = cursorColor,
    errorCursorColor = errorCursorColor,

    textSelectionColors = textSelectionColors,

    focusedLeadingIconColor = focusedLeadingIconColor.alphaDecreased(0.9f),
    unfocusedLeadingIconColor = unfocusedLeadingIconColor.alphaDecreased(),
    disabledLeadingIconColor = disabledLeadingIconColor.alphaDecreased(),
    errorLeadingIconColor = errorLeadingIconColor.alphaDecreased(),

    focusedTrailingIconColor = focusedTrailingIconColor.alphaDecreased(0.9f),
    unfocusedTrailingIconColor = unfocusedTrailingIconColor.alphaDecreased(),
    disabledTrailingIconColor = disabledTrailingIconColor.alphaDecreased(),
    errorTrailingIconColor = errorTrailingIconColor.alphaDecreased(),

    focusedPlaceholderColor = focusedPlaceholderColor.alphaDecreased(0.9f),
    unfocusedPlaceholderColor = unfocusedPlaceholderColor.alphaDecreased(),
    disabledPlaceholderColor = disabledPlaceholderColor.alphaDecreased(),
    errorPlaceholderColor = errorPlaceholderColor.alphaDecreased(0.8f),

    focusedSupportingTextColor = focusedSupportingTextColor.alphaDecreased(0.9f),
    unfocusedSupportingTextColor = unfocusedSupportingTextColor.alphaDecreased(),
    disabledSupportingTextColor = disabledSupportingTextColor.alphaDecreased(),
    errorSupportingTextColor = errorSupportingTextColor.alphaDecreased(),

    focusedPrefixColor = EmptyColor,
    unfocusedPrefixColor = EmptyColor,
    disabledPrefixColor = EmptyColor,
    errorPrefixColor = EmptyColor,

    focusedSuffixColor = EmptyColor,
    unfocusedSuffixColor = EmptyColor,
    disabledSuffixColor = EmptyColor,
    errorSuffixColor = EmptyColor,

    focusedLabelColor = EmptyColor,
    unfocusedLabelColor = EmptyColor,
    disabledLabelColor = EmptyColor,
    errorLabelColor = EmptyColor,

    focusedIndicatorColor = EmptyColor,
    unfocusedIndicatorColor = EmptyColor,
    disabledIndicatorColor = EmptyColor,
    errorIndicatorColor = EmptyColor,
)

@Composable
fun secondaryInputFieldColors(
    focusedTextColor: Color = DesignSystem.colors.onSecondary,
    unfocusedTextColor: Color = DesignSystem.colors.onSecondary,
    disabledTextColor: Color = DesignSystem.colors.disabledOnSecondary,
    errorTextColor: Color = DesignSystem.colors.errorSecondary,

    focusedContainerColor: Color = DesignSystem.colors.secondary,
    unfocusedContainerColor: Color = DesignSystem.colors.secondary,
    disabledContainerColor: Color = DesignSystem.colors.disabledSecondary,
    errorContainerColor: Color = DesignSystem.colors.secondary,

    cursorColor: Color = DesignSystem.colors.secondary,
    errorCursorColor: Color = DesignSystem.colors.errorSecondary,

    textSelectionColors: TextSelectionColors = LocalTextSelectionColors.current,

    focusedLeadingIconColor: Color = DesignSystem.colors.onSecondary,
    unfocusedLeadingIconColor: Color = DesignSystem.colors.onSecondary,
    disabledLeadingIconColor: Color = DesignSystem.colors.disabledOnSecondary,
    errorLeadingIconColor: Color = DesignSystem.colors.errorSecondary,

    focusedTrailingIconColor: Color = DesignSystem.colors.onSecondary,
    unfocusedTrailingIconColor: Color = DesignSystem.colors.onSecondary,
    disabledTrailingIconColor: Color = DesignSystem.colors.disabledOnSecondary,
    errorTrailingIconColor: Color = DesignSystem.colors.errorSecondary,

    focusedPlaceholderColor: Color = DesignSystem.colors.onSecondary,
    unfocusedPlaceholderColor: Color = DesignSystem.colors.onSecondary,
    disabledPlaceholderColor: Color = DesignSystem.colors.disabledOnSecondary,
    errorPlaceholderColor: Color = DesignSystem.colors.errorSecondary,

    focusedSupportingTextColor: Color = DesignSystem.colors.onSecondary,
    unfocusedSupportingTextColor: Color = DesignSystem.colors.onSecondary,
    disabledSupportingTextColor: Color = DesignSystem.colors.disabledOnSecondary,
    errorSupportingTextColor: Color = DesignSystem.colors.errorSecondary,
) = TextFieldColors(
    focusedTextColor = focusedTextColor,
    unfocusedTextColor = unfocusedTextColor.alphaDecreased(),
    disabledTextColor = disabledTextColor,
    errorTextColor = errorTextColor,

    focusedContainerColor = focusedContainerColor,
    unfocusedContainerColor = unfocusedContainerColor.alphaDecreased(),
    disabledContainerColor = disabledContainerColor,
    errorContainerColor = errorContainerColor,

    cursorColor = cursorColor,
    errorCursorColor = errorCursorColor,

    textSelectionColors = textSelectionColors,

    focusedLeadingIconColor = focusedLeadingIconColor.alphaDecreased(0.9f),
    unfocusedLeadingIconColor = unfocusedLeadingIconColor.alphaDecreased(),
    disabledLeadingIconColor = disabledLeadingIconColor.alphaDecreased(),
    errorLeadingIconColor = errorLeadingIconColor.alphaDecreased(),

    focusedTrailingIconColor = focusedTrailingIconColor.alphaDecreased(0.9f),
    unfocusedTrailingIconColor = unfocusedTrailingIconColor.alphaDecreased(),
    disabledTrailingIconColor = disabledTrailingIconColor.alphaDecreased(),
    errorTrailingIconColor = errorTrailingIconColor.alphaDecreased(),

    focusedPlaceholderColor = focusedPlaceholderColor.alphaDecreased(0.9f),
    unfocusedPlaceholderColor = unfocusedPlaceholderColor.alphaDecreased(),
    disabledPlaceholderColor = disabledPlaceholderColor.alphaDecreased(),
    errorPlaceholderColor = errorPlaceholderColor.alphaDecreased(0.8f),

    focusedSupportingTextColor = focusedSupportingTextColor.alphaDecreased(0.9f),
    unfocusedSupportingTextColor = unfocusedSupportingTextColor.alphaDecreased(),
    disabledSupportingTextColor = disabledSupportingTextColor.alphaDecreased(),
    errorSupportingTextColor = errorSupportingTextColor.alphaDecreased(),

    focusedPrefixColor = EmptyColor,
    unfocusedPrefixColor = EmptyColor,
    disabledPrefixColor = EmptyColor,
    errorPrefixColor = EmptyColor,

    focusedSuffixColor = EmptyColor,
    unfocusedSuffixColor = EmptyColor,
    disabledSuffixColor = EmptyColor,
    errorSuffixColor = EmptyColor,

    focusedLabelColor = EmptyColor,
    unfocusedLabelColor = EmptyColor,
    disabledLabelColor = EmptyColor,
    errorLabelColor = EmptyColor,

    focusedIndicatorColor = EmptyColor,
    unfocusedIndicatorColor = EmptyColor,
    disabledIndicatorColor = EmptyColor,
    errorIndicatorColor = EmptyColor,
)