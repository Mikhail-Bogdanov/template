package com.evo.storage


sealed interface StorageKey<D> {
    val name: String
    val defaultValue: D

    data class IntKey(
        override val name: String,
        override val defaultValue: Int,
    ) : StorageKey<Int>

    data class StringKey(
        override val name: String,
        override val defaultValue: String,
    ) : StorageKey<String>

    data class BooleanKey(
        override val name: String,
        override val defaultValue: Boolean,
    ) : StorageKey<Boolean>

    data class FloatKey(
        override val name: String,
        override val defaultValue: Float,
    ) : StorageKey<Float>
}