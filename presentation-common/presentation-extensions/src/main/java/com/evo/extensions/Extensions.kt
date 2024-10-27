package com.evo.extensions

import androidx.compose.ui.Modifier

typealias Lambda = () -> Unit

fun Modifier.conditional(
    condition: Boolean,
    modifier: Modifier.() -> Modifier,
) = if (condition) {
    then(modifier(Modifier))
} else {
    this
}

/**
 * @return updated modifier if value != null
 */
fun <V> Modifier.ifNotNullValue(
    value: V?,
    modifier: Modifier.(V) -> Modifier,
) = if (value != null) {
    then(modifier(Modifier, value))
} else {
    this
}

/**
 * @return updated modifier if value != null
 */
fun <V> Modifier.ifNotNullValue(
    value: V?,
    modifier: Modifier.(V) -> Modifier,
    elseModifier: Modifier.() -> Modifier,
) = if (value != null) {
    then(modifier(Modifier, value))
} else {
    then(elseModifier(Modifier))
}