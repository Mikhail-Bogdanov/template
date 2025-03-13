package com.evo.domain

/**
 * @return updated object if condition == true
 */
fun <T> T.conditional(condition: Boolean, operation: T.() -> T) = if (condition) {
    operation(this)
} else {
    this
}

/**
 * @return updated object if value != null
 */
fun <T, V> T.ifNotNull(
    value: V?,
    operation: T.(V) -> T,
) = if (value != null) operation(value) else this

/**
 * @return updated object if value != null or elseOperation object
 */
fun <T, V> T.ifNotNull(
    value: V?,
    operation: T.(V) -> T,
    elseOperation: T.() -> T,
) = if (value != null) operation(value) else elseOperation()