package com.evo.domain.extensions

import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineScope
import kotlin.math.roundToInt

typealias Lambda = () -> Unit

typealias SuspendLambda = suspend () -> Unit

typealias CoroutineLambda = suspend CoroutineScope.() -> Unit

private infix fun Double.percentOf(other: Double) = (other / 100.0 * this)
infix fun Int.percentOf(other: Double) = toDouble() percentOf other
infix fun Int.percentOf(other: Float) = toDouble() percentOf other.toDouble()
infix fun Int.percentOf(other: Int) = toDouble() percentOf other.toDouble()
infix fun Int.percentOf(other: Long) = toDouble() percentOf other.toDouble()

infix fun Int.percentValueOf(other: Int) = if (other != 0) {
    (this / other.toFloat() * 100f).roundToInt()
} else {
    0
}

infix fun Int.percentValueOf(other: Float) = if (other != 0f) {
    (this / other * 100f).roundToInt()
} else {
    0
}

infix fun Float.percentValueOf(other: Float) = if (other != 0f) {
    (this / other * 100f).roundToInt()
} else {
    0
}

infix fun Float.percentValueOf(other: Int) = if (other != 0) {
    (this / other * 100f).roundToInt()
} else {
    0
}

fun <T> stubList(size: Int = 12) = List<T?>(size) { null }.toPersistentList()

fun CharSequence.matches(regexes: Iterable<Regex>) = regexes.any { matches(it) }
fun CharSequence.matches(regex: String) = matches(Regex(regex))

infix operator fun Int.times(b: Boolean) = takeIf { b } ?: 0
infix operator fun Float.times(b: Boolean) = takeIf { b } ?: 0f
infix operator fun Long.times(b: Boolean) = takeIf { b } ?: 0L
infix operator fun Double.times(b: Boolean) = takeIf { b } ?: 0.0

fun <T> T?.orEmptyString() = this?.toString() ?: ""
