package com.evo.presentation.ui

import androidx.compose.ui.Modifier

/**
 * @return updated object if condition == true
 */
inline fun Modifier.conditional(
    condition: Boolean,
    operation: Modifier.() -> Modifier,
) = if (condition) {
    operation()
} else {
    this
}

/**
 * @return updated with [operation] object if condition == true or  updated with [elseOperation]
 */
inline fun Modifier.conditional(
    condition: Boolean,
    operation: Modifier.() -> Modifier,
    elseOperation: Modifier.() -> Modifier,
) = if (condition) {
    operation()
} else {
    elseOperation()
}

/**
 * @return updated object if value != null
 */
inline fun <V> Modifier.ifNotNull(
    value: V?,
    operation: Modifier.(V) -> Modifier,
) = if (value != null) operation(value) else this

/**
 * @return updated object if value != null or elseOperation object
 */
inline fun <V> Modifier.ifNotNull(
    value: V?,
    operation: Modifier.(V) -> Modifier,
    elseOperation: Modifier.() -> Modifier,
) = if (value != null) operation(value) else elseOperation()

/**
 * @return updated object if value is T
 */
inline fun <V, reified T> Modifier.conditionalIs(
    value: V,
    operation: Modifier.(T) -> Modifier,
) = if (value is T) operation(value) else this

/**
 * @return updated object if value is T or elseOperation object
 */
inline fun <V, reified T> Modifier.conditionalIs(
    value: V,
    operation: Modifier.(T) -> Modifier,
    elseOperation: Modifier.() -> Modifier,
) = if (value is T) operation(value) else elseOperation()
