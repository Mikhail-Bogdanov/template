package com.evo.storage

sealed interface EvoStorageSpec<D> {

    val name: String
    val defaultValue: D

    data class IntSpec(
        override val name: String,
        override val defaultValue: Int,
    ) : EvoStorageSpec<Int>

    data class LongSpec(
        override val name: String,
        override val defaultValue: Long,
    ) : EvoStorageSpec<Long>

    data class StringSpec(
        override val name: String,
        override val defaultValue: String,
    ) : EvoStorageSpec<String>

    data class BooleanSpec(
        override val name: String,
        override val defaultValue: Boolean,
    ) : EvoStorageSpec<Boolean>

    data class FloatSpec(
        override val name: String,
        override val defaultValue: Float,
    ) : EvoStorageSpec<Float>

    data class DoubleSpec(
        override val name: String,
        override val defaultValue: Double,
    ) : EvoStorageSpec<Double>
}
