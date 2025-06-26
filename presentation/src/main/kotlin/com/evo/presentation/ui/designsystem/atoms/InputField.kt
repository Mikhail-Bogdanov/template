package com.evo.presentation.ui.designsystem.atoms

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import com.evo.presentation.ui.designsystem.theme.*
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@Stable
class InputFieldState<T : Any>(
    initialValue: String,
    private val validate: (String) -> Boolean = { true },
    private val map: (input: String) -> T,
    private val maxTextLength: Int = Int.MAX_VALUE,
    debounce: Long = 200,
) {

    internal var stringValue by mutableStateOf(initialValue)
        private set
    var isInputValid by mutableStateOf(validate(stringValue))
        private set

    var value by mutableStateOf(mapIfValid(stringValue))
        private set

    val inputClearFlow = snapshotFlow { value }.filterNotNull().distinctUntilChanged()

    @OptIn(FlowPreview::class)
    val inputDebounceFlow = inputClearFlow.debounce(debounce)

    val unsafeValue get() = value!!

    fun clear() = onNewString("")

    val isEmpty get() = stringValue.isEmpty()

    private fun mapIfValid(newString: String) = if (isInputValid) map(newString) else null

    internal fun onNewString(newString: String) {
        if (newString.length > maxTextLength) return
        stringValue = newString
        isInputValid = validate(newString)
        val newValue = mapIfValid(newString)
        value = newValue
    }
}

@Composable
fun <T : Any> rememberInputFieldState(
    initialValue: String,
    validate: (String) -> Boolean = { true },
    map: (input: String) -> T,
    debounce: Long = 400,
): InputFieldState<T> {
    return remember {
        InputFieldState(initialValue, validate, map, debounce = debounce)
    }
}

@Composable
fun <T : Any> DesignSystem.InputField(
    modifier: Modifier = Modifier,
    state: InputFieldState<T>,
    enabled: Boolean = true,
    style: TextStyle = DesignSystem.TextStyles.body,
    placeholder: String? = null,
    supportingText: String? = null,
    leadingIcon: DSIcon? = null,
    trailingIcon: DSIcon? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    isError: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    shape: DesignSystem.Shapes = DesignSystem.Shapes.Medium,
    colors: InputFieldColors = primaryInputFieldColors(),
    interactionSource: MutableInteractionSource? = null,
) {

    val contentColor = when {
        enabled.not() -> colors.textColorDisabled
        state.isInputValid.not() || isError -> Colors.content.danger
        else -> colors.textColor
    }

    TextField(
        modifier = modifier,
        value = state.stringValue,
        onValueChange = state::onNewString,
        visualTransformation = visualTransformation,
        enabled = enabled,
        textStyle = style,
        placeholder = placeholder?.let {
            {
                Text(
                    text = it,
                    style = DesignSystem.TextStyles.description,
                    softWrap = false,
                    color = contentColor.alphaDecreased(0.5f),
                )
            }
        },
        leadingIcon = leadingIcon?.let {
            {
                val finalLeadingIcon = it.copy(
                    colors = IconColors(
                        contentColor = contentColor,
                        disabledContentColor = contentColor,
                        containerColor = EmptyColor,
                        disabledContainerColor = EmptyColor,
                    )
                )
                Icon(icon = finalLeadingIcon)
            }
        },
        trailingIcon = trailingIcon?.let {
            {
                val finalTrailingIcon = it.copy(
                    colors = IconColors(
                        contentColor = contentColor,
                        disabledContentColor = contentColor,
                        disabledContainerColor = EmptyColor,
                        containerColor = EmptyColor,
                    )
                )
                Icon(icon = finalTrailingIcon)
            }
        },
        supportingText = supportingText?.let {
            {
                Text(
                    text = it,
                    style = DesignSystem.TextStyles.description,
                    softWrap = false,
                    color = contentColor.alphaDecreased(),
                )
            }
        },
        isError = state.isInputValid.not() || isError,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        shape = shape.shape,
        colors = colors.toMaterialInputFieldColors(),
        interactionSource = interactionSource,
    )
}

data class InputFieldColors(
    val textColor: Color,
    val textColorDisabled: Color,
    val containerColor: Color,
    val containerColorDisabled: Color,
    val textSelectionColors: TextSelectionColors,
)

@Composable
fun DesignSystem.primaryInputFieldColors(
    textColor: Color = Colors.content.primary,
    textColorDisabled: Color = Colors.content.disabled,
    containerColor: Color = Colors.container.primary,
    containerColorDisabled: Color = Colors.container.disabled,
) = InputFieldColors(
    textColor,
    textColorDisabled,
    containerColor,
    containerColorDisabled,
    TextSelectionColors(
        handleColor = textColor.alphaDecreased(),
        backgroundColor = Colors.background.level2.alphaDecreased(0.4f),
    ),
)

@Composable
fun DesignSystem.secondaryInputFieldColors(
    textColor: Color = Colors.content.primary,
    textColorDisabled: Color = Colors.content.disabled,
    containerColor: Color = Colors.container.secondary,
    containerColorDisabled: Color = Colors.container.disabled,
) = InputFieldColors(
    textColor,
    textColorDisabled,
    containerColor,
    containerColorDisabled,
    TextSelectionColors(
        handleColor = textColor.alphaDecreased(),
        backgroundColor = Colors.background.level2.alphaDecreased(0.4f),
    ),
)

@Composable
private fun InputFieldColors.toMaterialInputFieldColors() = TextFieldColors(
    focusedTextColor = textColor,
    unfocusedTextColor = textColor.alphaDecreased(),
    disabledTextColor = textColorDisabled,
    errorTextColor = DesignSystem.Colors.content.danger,

    focusedContainerColor = containerColor,
    unfocusedContainerColor = containerColor.alphaDecreased(),
    disabledContainerColor = containerColorDisabled,
    errorContainerColor = DesignSystem.Colors.container.danger,

    cursorColor = textColor,
    errorCursorColor = DesignSystem.Colors.content.danger,

    textSelectionColors = textSelectionColors,

    focusedLeadingIconColor = DesignSystem.EmptyColor,
    unfocusedLeadingIconColor = DesignSystem.EmptyColor,
    disabledLeadingIconColor = DesignSystem.EmptyColor,
    errorLeadingIconColor = DesignSystem.EmptyColor,

    focusedTrailingIconColor = DesignSystem.EmptyColor,
    unfocusedTrailingIconColor = DesignSystem.EmptyColor,
    disabledTrailingIconColor = DesignSystem.EmptyColor,
    errorTrailingIconColor = DesignSystem.EmptyColor,

    focusedPlaceholderColor = DesignSystem.EmptyColor,
    unfocusedPlaceholderColor = DesignSystem.EmptyColor,
    disabledPlaceholderColor = DesignSystem.EmptyColor,
    errorPlaceholderColor = DesignSystem.EmptyColor,

    focusedSupportingTextColor = DesignSystem.EmptyColor,
    unfocusedSupportingTextColor = DesignSystem.EmptyColor,
    disabledSupportingTextColor = DesignSystem.EmptyColor,
    errorSupportingTextColor = DesignSystem.EmptyColor,

    focusedPrefixColor = DesignSystem.EmptyColor,
    unfocusedPrefixColor = DesignSystem.EmptyColor,
    disabledPrefixColor = DesignSystem.EmptyColor,
    errorPrefixColor = DesignSystem.EmptyColor,

    focusedSuffixColor = DesignSystem.EmptyColor,
    unfocusedSuffixColor = DesignSystem.EmptyColor,
    disabledSuffixColor = DesignSystem.EmptyColor,
    errorSuffixColor = DesignSystem.EmptyColor,

    focusedLabelColor = DesignSystem.EmptyColor,
    unfocusedLabelColor = DesignSystem.EmptyColor,
    disabledLabelColor = DesignSystem.EmptyColor,
    errorLabelColor = DesignSystem.EmptyColor,

    focusedIndicatorColor = DesignSystem.EmptyColor,
    unfocusedIndicatorColor = DesignSystem.EmptyColor,
    disabledIndicatorColor = DesignSystem.EmptyColor,
    errorIndicatorColor = DesignSystem.EmptyColor,
)
